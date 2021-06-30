package com.uber.pubsub.service;

import com.uber.pubsub.entity.Message;
import com.uber.pubsub.entity.MessageDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akshay on 29/06/21
 */
@Getter
@Setter
public abstract class QueueService {
    public QueueService(String queueId, long messageRetentionTime) {
        this.queueId = queueId;
        this.messageRetentionTime = messageRetentionTime;
    }

    String queueId;
    long messageRetentionTime;

    public abstract boolean publish(MessageDTO messageDTO);
    public abstract boolean registerConsumer(Consumer consumer);
    public abstract Message getHead();

    // TODO: 29/06/21 handler time, handler validations, etc
}
