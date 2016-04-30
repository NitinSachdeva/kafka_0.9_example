package com.SankDis.Producer.Simulator;

import com.SankDis.common.model.MessageKey;
import com.SankDis.common.model.MessageValue;

/**
 * Created by nsachdeva on 4/30/16.
 */
public interface IProducerMessageSimulator
{
    /**
     * Get the next message key
     * @return
     */
    public MessageKey getMessageKey();

    /**
     * get next message value
     * @return
     */
    public MessageValue getMessagevalue();
}
