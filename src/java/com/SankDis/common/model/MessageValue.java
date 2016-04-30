package com.SankDis.common.model;

import java.io.Serializable;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class MessageValue implements Serializable
{
    private String action;
    private Double amount;

    public MessageValue( String action, Double amount)
    {
        this.action = action;
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return "MessageValue{" +
                "action='" + action + '\'' +
                ", amount=" + amount +
                '}';
    }
}
