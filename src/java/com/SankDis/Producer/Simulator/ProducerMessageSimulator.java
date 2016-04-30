package com.SankDis.Producer.Simulator;

import com.SankDis.common.model.MessageKey;
import com.SankDis.common.model.MessageValue;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class ProducerMessageSimulator implements IProducerMessageSimulator
{
    private static Long count = 0l;

    private static Long getNextInt()
    {
        return (count ++) % Long.MAX_VALUE;
    }

    private static final List<String> ACTION = new ArrayList<>(2);

    {
        ACTION.add("CREDIT");
        ACTION.add("DEBIT");
    }

    public MessageKey getMessageKey()
    {
        MessageKey mk = new MessageKey(RandomUtils.nextLong(0,Long.MAX_VALUE),getNextInt());
        System.out.println(mk);
        return mk;
    }

    @Override
    public MessageValue getMessagevalue()
    {
        return new MessageValue(ACTION.get(RandomUtils.nextInt(0,2)),
                RandomUtils.nextDouble(0,Double.MAX_VALUE));
    }
}

