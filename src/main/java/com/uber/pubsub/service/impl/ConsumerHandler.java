package com.uber.pubsub.service.impl;

import com.uber.pubsub.entity.Message;
import com.uber.pubsub.service.Consumer;
import com.uber.pubsub.service.QueueService;
import lombok.SneakyThrows;

import java.util.Objects;

/**
 * @author akshay on 29/06/21
 */
public class ConsumerHandler implements Runnable{

    private Consumer consumer;

    QueueService queueService;
    Message currentMessage;

    public ConsumerHandler(Consumer consumer, QueueService queueService) {
        this.consumer = consumer;
        this.queueService = queueService;
    }

    @Override
    @SneakyThrows
    public void run() {
        while(true){

            if(Objects.isNull(currentMessage)){
                currentMessage=queueService.getHead();
            }

            if(Objects.nonNull(currentMessage) && System.currentTimeMillis()> currentMessage.getReceivedTime()+currentMessage.getTtl()){
                System.out.println("TTL Expired"+ currentMessage);
                // TODO: 29/06/21  delete from Queue
            }else{
                consumer.consume(currentMessage);
                if(Objects.isNull(currentMessage.getNext())){
                    System.out.println("Waiting for messages");
                    synchronized (this) {
                        this.wait();
                    }
                }
                currentMessage=currentMessage.getNext();
            }
        }
    }

    public void invoke(){
        synchronized (this){
            this.notify();
        }
    }

}
