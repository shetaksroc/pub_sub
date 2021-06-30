package com.uber.test;

import com.uber.pubsub.app.QueueLibrary;
import com.uber.pubsub.entity.MessageDTO;
import com.uber.pubsub.exception.QueueAlreadyExistsException;
import com.uber.pubsub.service.Consumer;
import com.uber.pubsub.service.impl.ConsumerImpl;

/**
 * @author akshay on 29/06/21
 */
public class QueueApplicationTest {

    public static void main(String[] args) throws QueueAlreadyExistsException {
        QueueLibrary queueLibrary = new QueueLibrary();
        queueLibrary.createQueue("q1", 1000);
        Consumer consumer = new ConsumerImpl("c1");
        Consumer consumer2 = new ConsumerImpl("c2");

        queueLibrary.registerConsumer("q1", consumer);
        queueLibrary.registerConsumer("q1", consumer2);

        MessageDTO messageDTO = new MessageDTO("M1", 1000);
        queueLibrary.sendMessage("q1", messageDTO);
        MessageDTO messageDTO2 = new MessageDTO("M2", 1000);
        queueLibrary.sendMessage("q1", messageDTO2);
    }
}
