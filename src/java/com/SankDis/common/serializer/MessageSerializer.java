package com.SankDis.common.serializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class MessageSerializer<T extends Serializable> implements Serializer<T>
{
    @Override
    public void configure(Map configs, boolean isKey)
    {

    }

    @Override
    public byte[] serialize(String topic, T data)
    {
        return SerializationUtils.serialize(data);
    }

    @Override
    public void close()
    {

    }
}
