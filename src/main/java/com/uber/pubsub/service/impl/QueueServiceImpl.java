package com.uber.pubsub.service.impl;

import com.uber.pubsub.entity.Message;
import com.uber.pubsub.entity.MessageDTO;
import com.uber.pubsub.service.Consumer;
import com.uber.pubsub.service.QueueService;
import lombok.Getter;

import java.util.*;

/**
 * @author akshay on 29/06/21
 */
@Getter
public class QueueServiceImpl extends QueueService {

    private Message messageQueue;
    private Message head;
    private Message tail;
    private final Map<String, ConsumerHandler> consumerDetails = new HashMap<String, ConsumerHandler>();

    Set<String> consumerExecutors = new HashSet<>();
    public QueueServiceImpl(String queueId, long messageRetentionTime) {
        super(queueId, messageRetentionTime);
    }

    // shubhamjain@uber.com
    @Override
    public boolean publish(MessageDTO messageDto) {
        Message message = new Message(messageDto.getBody(), messageDto.getTtl());
        message.setReceivedTime(System.currentTimeMillis());
        if(Objects.isNull(messageQueue)){
            messageQueue=message;
            head=message;
            tail=message;
        }else{
            tail.setNext(message);
            message.setPrev(tail);
            tail = message;
        }
        invokeConsumers();
        return true;
    }

    private void invokeConsumers() {
        for(Map.Entry<String, ConsumerHandler> consumerHandlerEntry: consumerDetails.entrySet()){
            if(consumerExecutors.contains(consumerHandlerEntry.getKey())){
                consumerHandlerEntry.getValue().invoke();
            }else{
                consumerExecutors.add(consumerHandlerEntry.getKey());
                new Thread(consumerHandlerEntry.getValue()).start();
            }
        }
    }

    @Override
    public boolean registerConsumer(Consumer consumer) {
        consumerDetails.put(consumer.getConsumerId(), new ConsumerHandler(consumer, this));
        return true;
    }
}
