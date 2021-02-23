package com.voteservice.amqp.implementation;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voteservice.amqp.AmqpProducer;
import com.voteservice.config.producer.RabbitMqProducerConfig;
import com.voteservice.dto.MessageQueueDTO;
/**
* <p>**************************************************************************************
* <p>                                 RabbitMQProducer
* <p>**************************************************************************************
* <p>
* This code implements AmqpProducer interface.
* Send message through RabbitMQ massager
* <p>
* @param  MessageQueueDTO - Data Transfer Object called MessageQueue used
*/
@Component
public class RabbitMQProducer implements AmqpProducer<MessageQueueDTO>{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private RabbitMqProducerConfig rabbitMqProcedureConfig;

	@Override
    public void producer(MessageQueueDTO message) {
        try {
            rabbitTemplate.convertAndSend(rabbitMqProcedureConfig.getExchange(),rabbitMqProcedureConfig.getQueue(), message);
        } catch (Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
	


}
