package com.SankDis.consumer.LogWriter;

import com.SankDis.common.model.MessageKey;
import com.SankDis.common.model.MessageValue;
import com.SankDis.consumer.MessageConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class LogWriter implements ILogWriter
{
    private static final String COUNT_MESSAGE = " Number of records records in poll is : ";

    @Override
    public void writeLogs(ConsumerRecords<MessageKey, MessageValue> records)
    {
        File file = new File("/tmp/Kafka-Log.log");

        try(FileOutputStream fop = new FileOutputStream(file,true) )
        {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created a file " + file.getPath());
            }
            writeCountMessage(fop,records.count());
            writeMessage(fop,records);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    private void writeCountMessage(FileOutputStream fp,Integer records) throws IOException
    {
        StringBuilder sb = new StringBuilder(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()))
                .append(COUNT_MESSAGE)
                .append(records)
                .append(" Consumer ID ")
                .append(MessageConsumer.consumerId);

        fp.write(sb.toString().getBytes());
        fp.write(System.getProperty("line.separator").getBytes());
    }

    private void writeMessage(FileOutputStream fp,ConsumerRecords<MessageKey,MessageValue> consumerRecords) throws IOException
    {
        for(ConsumerRecord<MessageKey,MessageValue> record : consumerRecords)
        {
            StringBuilder sb = new StringBuilder(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())).
                    append(" MessageKey : ").
                    append(record.key().toString())
                    .append(" --- ")
                    .append(" MessageValue : ")
                    .append(record.value().toString())
                    .append(" Paritition Number :  ")
                    .append( record.partition() +  " ")
                    .append(" Consumer ID ")
                    .append(MessageConsumer.consumerId);

            fp.write(sb.toString().getBytes());
            fp.write(System.getProperty("line.separator").getBytes());
            record.partition();
        }
    }
}
