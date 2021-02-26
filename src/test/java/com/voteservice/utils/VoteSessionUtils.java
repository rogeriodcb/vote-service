package com.voteservice.utils;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.voteservice.model.VoteSession;

/**
* <p>**************************************************************************************
* <p>                                 VoteSessionUtils
* <p>**************************************************************************************
* <p>
* This class produces faker data to be used in the tests of VoteSession controller.
*/
public class VoteSessionUtils {
	private static final Faker faker = Faker.instance();
	
	// creates VoteSession to register
	public static VoteSession createFakeVoteSession() {
		LocalDateTime dt = LocalDateTime.now();
        return VoteSession.builder()
        		.voteSessionId(faker.number().randomNumber())
                .dateTimeToStart(dt)
                .dateTimeToEnd(dt.plusMinutes(1))
                .status("standby")
                .voteTopicId(VoteTopicUtils.createFakeVoteTopic())
                .build();
 
    }
	
	
	// converts object to JsonString
	public static String asJsonString(VoteSession voteSession) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(voteSession);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
