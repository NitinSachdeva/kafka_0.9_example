package com.SankDis.common.model;

import java.io.Serializable;

/**
 * Created by nsachdeva on 4/30/16.
 */
public class MessageKey implements Serializable
{
    private Long id;
    private Long organizationId;

    public MessageKey(Long id, Long organizationId)
    {
        this.id = id;
        this.organizationId = organizationId;
    }

    public Long getId()
    {
        return id;
    }

    public Long getOrganizationId()
    {
        return organizationId;
    }

    @Override
    public String toString()
    {
        return "MessageKey{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                '}';
    }
}
