package com.voteservice.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.voteservice.model.VoteTopic;


/**
* <p>**************************************************************************************
* <p>                                 VoteTopicUtils
* <p>**************************************************************************************
* <p>
* This class produces faker data to be used in the tests of VoteTopic controller.
*/
public class VoteTopicUtils {
	
	private static final Faker faker = Faker.instance();
	
	// creates VoteTopic to register
	public static VoteTopic createFakeVoteTopic() {
        return VoteTopic.builder()
        		.voteTopicId(faker.number().randomNumber())
                .name(faker.book().author())
                .description("Test description")
                .build();
 
    }
	
	
	// converts object to JsonString
	public static String asJsonString(VoteTopic voteTopic) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(voteTopic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


