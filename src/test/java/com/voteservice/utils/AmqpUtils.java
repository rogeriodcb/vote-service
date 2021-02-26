package com.voteservice.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.voteservice.dto.MessageQueueDTO;

/**
* <p>**************************************************************************************
* <p>                                 AmqpUtils
* <p>**************************************************************************************
* <p>
* This class is used to provide methods to be use in the Amqp testsO.
*/
public class AmqpUtils {
	// converts object to JsonString
		public static String asJsonString(MessageQueueDTO messageQueueDTO) {
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	            objectMapper.registerModules(new JavaTimeModule());

	            return objectMapper.writeValueAsString(messageQueueDTO);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
