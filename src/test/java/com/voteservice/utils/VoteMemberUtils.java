package com.voteservice.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.voteservice.model.VoteMember;

/**
* <p>**************************************************************************************
* <p>                                 VoteMemberUtils
* <p>**************************************************************************************
* <p>
* This class produces faker data to be used in the tests of VoteMember controller.
*/
public class VoteMemberUtils {
	private static final Faker faker = Faker.instance();
	
	// creates VoteSession to register
	public static VoteMember createFakeVoteMember() {
		
        return VoteMember.builder()
        		.voteMemberId(faker.number().randomNumber())
        		.cpf("67097859040") // generated randomlyO
        		.vote(faker.bool().bool())
        		.voteTopic(VoteTopicUtils.createFakeVoteTopic())
                .build();
 
    }
	
	
	// converts object to JsonString
	public static String asJsonString(VoteMember voteMember) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(voteMember);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
