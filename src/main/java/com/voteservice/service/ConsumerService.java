package com.voteservice.service;

import com.voteservice.dto.MessageQueueDTO;

/**
* <p>**************************************************************************************
* <p>                                 ConsumerService
* <p>**************************************************************************************
* <p>
* This is a Consumer service interface
*/
public interface ConsumerService {
	void action(MessageQueueDTO message);
}
