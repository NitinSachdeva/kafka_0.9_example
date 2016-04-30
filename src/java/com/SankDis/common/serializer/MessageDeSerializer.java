package com.SankDis.common.serializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class MessageDeSerializer<T extends Serializable> implements Deserializer
{
    @Override
    public void configure(Map configs, boolean isKey)
    {

    }

    @Override
    public Object deserialize(String topic, byte[] data)
    {
        return data == null ? null : SerializationUtils.deserialize(data);
    }

    @Override
    public void close()
    {

    }
}
