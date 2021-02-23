package com.voteservice.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.voteservice.amqp.AmqpRePublish;
import com.voteservice.service.RePublishService;

/**
* <p>**************************************************************************************
* <p>                            RePublishServiceImplementation
* <p>**************************************************************************************
* <p>
* This is the implementation of RePublish service interface
*/
public class RePublishServiceImplementation implements RePublishService {

    @Autowired
    private AmqpRePublish rePublish;

    @Override
    public void repost() {
        rePublish.rePublish();
    }
}
