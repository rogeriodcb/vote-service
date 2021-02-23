package com.voteservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.voteservice.dto.MessageQueueDTO;
import com.voteservice.service.AmqpService;
/**
* <p>**************************************************************************************
* <p>                                 AmqpController
* <p>**************************************************************************************
* <p>
* This requisition provides only RabbitMq message web test. This Rest controller was live
* in this project to test and demonstrate.
* <p>Use Postman software or other one like it to send.
* <p>1) Enter url in web browser https://localhost:8081/send
* <p>2) in the body configures to RAW and JSON
* <p>3) put your message like example bellow
* <p> {
* <p>   "text":"This is a message test !"
* <p> }
*/
@RestController
public class AmqpController {
	@Autowired
    private AmqpService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/send")
    public void sendToConsumer(@RequestBody MessageQueueDTO message) {
        service.sendToConsumer(message);
    }
}
