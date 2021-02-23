package com.voteservice.service;

import com.voteservice.dto.MessageQueueDTO;

/**
* <p>**************************************************************************************
* <p>                                 AmqpService
* <p>**************************************************************************************
* <p>
* This is a AMQP service interface
*/
public interface AmqpService {
	void sendToConsumer(MessageQueueDTO message);
}
