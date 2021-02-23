package com.voteservice.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.voteservice.dto.MessageResponseDTO;
import com.voteservice.exception.VoteServiceException;
import com.voteservice.model.VoteMember;
import com.voteservice.model.VoteSession;
import com.voteservice.model.VoteTopic;
import com.voteservice.repository.VoteMemberRepository;
import com.voteservice.repository.VoteTopicRepository;

/**
* <p>**************************************************************************************
* <p>                                 VoteMemberService
* <p>**************************************************************************************
* <p>
* This service is responsible to implement some class methods for the Member transaction
*/
@Service
public class VoteMemberService {

	private static final String CPF_UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

	@Value("${user.info.url}")
	private String urlCpf = "";
	
	private final VoteMemberRepository voteMemberRepository;
	private final VoteTopicRepository voteTopicRepository;
	private final RestTemplate restTemplate;
	private final VoteSessionService voteSessionService;
	
	
	//constructor
	@Autowired
	public VoteMemberService(VoteMemberRepository voteMemberRepository, VoteTopicRepository voteTopicRepository,
			RestTemplate restTemplate, VoteSessionService voteSessionService) {
		this.voteMemberRepository = voteMemberRepository;
		this.voteTopicRepository = voteTopicRepository;
		this.restTemplate = restTemplate;
		this.voteSessionService = voteSessionService;
	}



	
	protected VoteMember verifyAndSave(VoteSession voteSession, VoteMember voteMember) {

		// check if session is opened
		if(voteSession.getStatus().equals("opened")){
			// check if the vote not exist
			if(!voteAlreadyExists(voteMember)) {
				// check if the CPF is valid and able
				if(cpfAbleToVote(voteMember)) {
					VoteMember savedVoteMember = voteMemberRepository.save(voteMember);
					
					System.out.println(MessageResponseDTO.builder()
							.message("Vote Member computed and created with ID " + savedVoteMember.getVoteMemberId())
							.build().getMessage());
					return savedVoteMember;

				}else
					throw new VoteServiceException("Member was not able to vote.");		

			}else
				throw new VoteServiceException("This member voted previously.");		

		}else if(voteSession.getStatus().equals("standby")){
			throw new VoteServiceException("Session is not open yet. Try to vote in the future.");

		}else{
			throw new VoteServiceException("Session was closed. Unable to vote in this topic.");
		}
	}
	
	// verify if the CPF is able to vote	
	protected boolean cpfAbleToVote(VoteMember vote) {
		ResponseEntity<String> cpfValidation = getCpfValidation(vote);

		if (HttpStatus.OK.equals(cpfValidation.getStatusCode())) {
			if (CPF_UNABLE_TO_VOTE.equalsIgnoreCase(cpfValidation.getStatusCode().toString())) {
				return false;
			}else
				return true;
		} else {
			return false;
		}
	}
	
	// use external CPF validator and verify if CPF is valid and able
	private ResponseEntity<String> getCpfValidation(VoteMember voteMember) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		try {
		ResponseEntity<String> rest = restTemplate.exchange(urlCpf.concat("/").concat(voteMember.getCpf()), HttpMethod.GET, entity,
				String.class);
		return rest;
		}catch(HttpClientErrorException httpClientErrorException) {
			throw new VoteServiceException("Http error. Possible causes: "
					+ "The CPF is not valid "
					+ "or An error ocurr while send request.\nError: "
					+httpClientErrorException.toString());
		}
	}
	
	// verify if CPF exists for the topic
	protected boolean voteAlreadyExists(VoteMember voteMember) {
		Optional<VoteMember> voteByCpfAndTopic = voteMemberRepository.findByCpfAndVoteTopic(voteMember.getCpf(), voteMember.getVoteTopic());

		if (voteByCpfAndTopic.isPresent())
			return true; // vote exist
		else
			return false;
	
	}	
	
	// public methods
	// create vote
	public VoteMember createVote(Long voteTopicId, VoteMember vote) {
		Optional<VoteSession> session = Optional.of(voteSessionService.findSessionByVoteTopicId(voteTopicId));
		if (!session.isPresent()) {
			throw new VoteServiceException("The vote topic id was not found.");
		}else {
			vote.setVoteTopic(voteTopicRepository.findById(voteTopicId).get());
			return verifyAndSave(session.get(), vote);
		}
		
	
	}
	
	// find member by id
	public VoteMember findById(Long voteMemberId) {
		Optional<VoteMember> voteMember = voteMemberRepository.findById(voteMemberId);
		if(!voteMember.isPresent())
			throw new VoteServiceException("The member id was not found.");
		else {
			return voteMember.get();
		}
	}
	
	// counts true (yes) votes
	public Long countVoteTrue(VoteTopic voteTopic) {
		return voteMemberRepository.countByVoteTopicAndVoteTrue(voteTopic);
	}
	
	// counts false (no) votes
	
	public Long countVoteFalse(VoteTopic voteTopic) {
		return voteMemberRepository.countByVoteTopicAndVoteFalse(voteTopic);
	}
}
