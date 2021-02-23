package com.voteservice.amqp.implementation;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voteservice.amqp.AmqpConsumer;
import com.voteservice.dto.MessageQueueDTO;
import com.voteservice.service.ConsumerService;

/**
* <p>**************************************************************************************
* <p>                                 RabbitMQConsumer
* <p>**************************************************************************************
* <p>
* This code implements AmqpConsumer interface.
* Get message by using Rabbit listener from RabbitMQ massager
* <p>
* @param  MessageQueueDTO - Data Transfer Object called MessageQueue used
*/
@Component
public class RabbitMQConsumer implements AmqpConsumer<MessageQueueDTO> {

    @Autowired
    private ConsumerService consumerService;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(MessageQueueDTO message) {
        try {
       		consumerService.action(message);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }

}
