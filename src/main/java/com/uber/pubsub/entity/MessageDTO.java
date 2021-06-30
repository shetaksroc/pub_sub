package com.uber.pubsub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author akshay on 29/06/21
 */
@Getter
@Setter
@AllArgsConstructor
public class MessageDTO {
    String body;
    long ttl;


}
