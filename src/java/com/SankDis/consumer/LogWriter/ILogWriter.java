package com.SankDis.consumer.LogWriter;

import com.SankDis.common.model.MessageKey;
import com.SankDis.common.model.MessageValue;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * Created by nsachdeva on 4/30/16.
 */
public interface ILogWriter
{
    /**
     * get to file in tmp for logging purpose.
     * @param records
     */
    void writeLogs(ConsumerRecords<MessageKey,MessageValue> records);
}
