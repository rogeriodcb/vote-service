package com.voteservice.dto;

import lombok.Builder;
import lombok.Data;

/**
* <p>**************************************************************************************
* <p>                                 MessageResponseDTO
* <p>**************************************************************************************
* <p>
* Data Transfer Object to be used as a message response.
*/
@Data
@Builder
public class MessageResponseDTO {
	
	private String message;

}
