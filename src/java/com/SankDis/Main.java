package com.SankDis;


import com.SankDis.Producer.MessageProducer;
import com.SankDis.consumer.MessageConsumer;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class Main
{
    private static String PRODUCER = "producer";
    private static String CONSUMER = "consumer";
    public static void main(String[] args)
    {
        if(args == null || args.length <1)
        {
            System.out.println("Enter correct arguments : producer/consumer");
            System.exit(0);
        }

        if(PRODUCER.equalsIgnoreCase(args[0]))
        {
            MessageProducer.main(args);
        }

        if(CONSUMER.equalsIgnoreCase(args[0]))
        {
            MessageConsumer.main(args);
        }
    }
}
