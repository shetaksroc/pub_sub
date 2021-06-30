package com.uber.pubsub.app;

import com.uber.pubsub.entity.MessageDTO;
import com.uber.pubsub.exception.QueueAlreadyExistsException;
import com.uber.pubsub.service.Consumer;
import com.uber.pubsub.service.QueueService;
import com.uber.pubsub.service.impl.QueueServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author akshay on 29/06/21
 */
public class QueueLibrary {
    Map<String, QueueService> map  = new HashMap<>();


    public void createQueue(String queueId, long messageRetentionTime) throws QueueAlreadyExistsException {
        if(map.containsKey(queueId)){
            throw new QueueAlreadyExistsException();
        }
        map.put(queueId,  new QueueServiceImpl(queueId, messageRetentionTime));
    }


    public void sendMessage(String queueId, MessageDTO messageDTO) throws QueueAlreadyExistsException {
        map.get(queueId).publish(messageDTO);
    }


    public void registerConsumer(String queueId, Consumer consumer) throws QueueAlreadyExistsException {
        map.get(queueId).registerConsumer(consumer);
    }



}
