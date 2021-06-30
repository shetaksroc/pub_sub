package com.uber.pubsub.service;

import com.uber.pubsub.entity.Message;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akshay on 29/06/21
 */
@Getter
@Setter
public abstract class Consumer {
    private String consumerId;

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    public abstract void consume(Message message);
}
