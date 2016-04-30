package com.SankDis.Producer;

import com.SankDis.Producer.Simulator.IProducerMessageSimulator;
import com.SankDis.Producer.Simulator.ProducerMessageSimulator;
import com.SankDis.common.model.MessageKey;
import com.SankDis.common.model.MessageValue;
import com.SankDis.consumer.MessageConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class MessageProducer
{
    private static final String topic = "testTopic";

    public static void main(String[] args)
    {
        Producer<MessageKey, MessageValue> producer = null;
        InputStream input = null;
        Properties prop = new Properties();
        try
        {
            input = MessageConsumer.class.getClassLoader().getResourceAsStream("com/SankDis/Producer/kafka-producer.properties");
            prop.load(input);
            producer = new KafkaProducer<MessageKey, MessageValue>(prop);

            IProducerMessageSimulator simulator = new ProducerMessageSimulator();

            int count = 0;
            while(true)
            {
                count++;
                ProducerRecord<MessageKey,MessageValue> record = new ProducerRecord<MessageKey, MessageValue>(topic,
                        simulator.getMessageKey(),simulator.getMessagevalue());
                producer.send(record);
                if(count > 100000)
                {
                    Thread.sleep(5000);
                    count = 0;
                }
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            closeInputStream(input);
            closeProducer(producer);
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

    private static void closeProducer(Producer producer)
    {
        if(producer != null)
            producer.close();
    }


}
