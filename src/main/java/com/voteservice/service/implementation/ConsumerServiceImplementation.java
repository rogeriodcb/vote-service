package com.voteservice.service.implementation;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

import com.voteservice.dto.MessageQueueDTO;
import com.voteservice.service.ConsumerService;

/**
* <p>**************************************************************************************
* <p>                            ConsumerServiceImplementation
* <p>**************************************************************************************
* <p>
* This is the implementation of consumer service interface
*/
@Service
public class ConsumerServiceImplementation implements ConsumerService{
	@Override
    public void action(MessageQueueDTO message) {
        if("teste".equalsIgnoreCase(message.getText())) {
            throw new AmqpRejectAndDontRequeueException("erro");
        }

        System.out.println(message.getText());
    }
}
