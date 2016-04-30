package com.SankDis.Producer.Partitioner;

import com.SankDis.common.model.MessageKey;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class ProducerPartitioner implements Partitioner
{
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster)
    {
        int returnParition = 0;
        MessageKey messageKey = (MessageKey)key;
        Integer availablePartitions = cluster.availablePartitionsForTopic(topic).size();
        if(availablePartitions > 0 && messageKey != null)
        {
            if(messageKey.getOrganizationId() < 100000)
                returnParition = 0;
            else
            {
                //returnParition = new HashCodeBuilder(17, 47).append(messageKey.getOrganizationId()).toHashCode();
                returnParition = messageKey.getOrganizationId().intValue() % availablePartitions;
            }
        }
        else
        {
            returnParition = RandomUtils.nextInt(0,cluster.partitionCountForTopic(topic));
        }

        return returnParition;
    }

    @Override
    public void close()
    {

    }

    @Override
    public void configure(Map<String, ?> configs)
    {

    }
}

