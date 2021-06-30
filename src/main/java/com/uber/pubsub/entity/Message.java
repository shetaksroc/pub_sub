package com.uber.pubsub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author akshay on 29/06/21
 */
@Getter
@Setter
public class Message {
    private String body;
    private final long ttl;
    private long receivedTime;

    public Message(String body, long ttl) {
        this.body = body;
        this.ttl = ttl;
    }

    private Message prev;
    private Message next;
}
