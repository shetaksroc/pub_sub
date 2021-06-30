package com.uber.pubsub.service.impl;

import com.uber.pubsub.entity.Message;
import com.uber.pubsub.service.Consumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * @author akshay on 29/06/21
 */
@Getter
public class ConsumerImpl extends Consumer {

    public ConsumerImpl(String consumerId) {
        super(consumerId);
    }

    @SneakyThrows
    @Override
    public void consume(Message message) {
        System.out.println("Consumed Message:"+ message);
        System.out.println("Sleeping for 5 secs:");
        Thread.sleep(5000);
    }
}
