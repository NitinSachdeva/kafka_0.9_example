package com.SankDis.consumer;

import com.SankDis.common.model.MessageKey;
import com.SankDis.common.model.MessageValue;
import com.SankDis.consumer.LogWriter.ILogWriter;
import com.SankDis.consumer.LogWriter.LogWriter;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class MessageConsumer
{
    private static final String topic = "testTopic";

    public static String consumerId = "Consumer1";

    public static void main(String[] args)
    {
        InputStream input = null;
        Properties prop = new Properties();

        if(args.length >= 2)
            consumerId = args[1];

        try
        {
            input = MessageConsumer.class.getClassLoader().getResourceAsStream("com/SankDis/consumer/kafka-consumer.properties");
            prop.load(input);
            System.out.println(prop.toString());
            KafkaConsumer<MessageKey, MessageValue> consumer = new KafkaConsumer<>(prop);
            consumer.subscribe(getTopicList());

            while (true)
            {
                ConsumerRecords<MessageKey, MessageValue> consumerRecord = consumer.poll(10000);
                ILogWriter logWriter = new LogWriter();
                logWriter.writeLogs(consumerRecord);
                consumer.commitSync();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeInputStream(input);

        }
    }

    private static void closeInputStream(InputStream input)
    {
        if(input != null)
            try
            {
                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }


    private static List<String> getTopicList()
    {
        List<String> topicList = new ArrayList<>();
        topicList.add(topic);
        return topicList;
    }


}
