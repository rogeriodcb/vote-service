package com.voteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voteservice.dto.MessageResponseDTO;
import com.voteservice.exception.VoteServiceException;
import com.voteservice.model.VoteTopic;
import com.voteservice.repository.VoteTopicRepository;

/**
* <p>**************************************************************************************
* <p>                                 VoteTopicService
* <p>**************************************************************************************
* <p>
* This service is responsible to implement some class methods for the Vote Topic transaction
*/
@Service
public class VoteTopicService {

	private VoteTopicRepository voteTopicRepository;

	// constructor
	@Autowired
	public VoteTopicService(VoteTopicRepository voteTopicRepository) {
        this.voteTopicRepository = voteTopicRepository;
    }
	
	// find all created vote topics
	public List<VoteTopic> findAll() {
        return voteTopicRepository.findAll();
    }

	// call repository to save new vote topic
    public MessageResponseDTO save(final VoteTopic voteTopic) {
    	VoteTopic savedVoteTopic = voteTopicRepository.save(voteTopic);
		return MessageResponseDTO.builder()
				.message("Vote Topic created with ID " + savedVoteTopic.getVoteTopicId())
				.build();
    }

    // find vote topic by Id
    public VoteTopic findById(Long id) {
        Optional<VoteTopic> findById = voteTopicRepository.findById(id);
        if(!findById.isPresent()){
            throw new VoteServiceException("Vote topic id was not found.");
        }
        return findById.get();
    }
}
