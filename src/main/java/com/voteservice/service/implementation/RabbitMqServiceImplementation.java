package com.voteservice.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voteservice.amqp.AmqpProducer;
import com.voteservice.dto.MessageQueueDTO;
import com.voteservice.service.AmqpService;

/**
* <p>**************************************************************************************
* <p>                            RabbitMqServiceImplementation
* <p>**************************************************************************************
* <p>
* This is the implementation of AMQP service interface
*/
@Service
public class RabbitMqServiceImplementation implements AmqpService{
	@Autowired
	private AmqpProducer<MessageQueueDTO> amqp;
	
	public void sendToConsumer(MessageQueueDTO message) {
		amqp.producer(message);		
	}
	
}
