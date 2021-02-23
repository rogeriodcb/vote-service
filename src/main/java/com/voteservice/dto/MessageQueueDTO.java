package com.voteservice.dto;

/**
* <p>**************************************************************************************
* <p>                                 MessageQueueDTO
* <p>**************************************************************************************
* <p>
* Data Transfer Object to be used in the Message Queue.
*/
public class MessageQueueDTO {
	private String text;

    public MessageQueueDTO() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
