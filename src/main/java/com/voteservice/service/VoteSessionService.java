package com.voteservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voteservice.exception.VoteServiceException;
import com.voteservice.model.VoteSession;
import com.voteservice.model.VoteTopic;
import com.voteservice.repository.VoteSessionRepository;
import com.voteservice.repository.VoteTopicRepository;

/**
* <p>**************************************************************************************
* <p>                                VoteSessionService
* <p>**************************************************************************************
* <p>
* This service is responsible to implement some class methods for the session transaction
*/
@Service
public class VoteSessionService {
	
	private VoteSessionRepository voteSessionRepository;
    private VoteTopicRepository voteTopicRepository;

	// constructor
	@Autowired
	public VoteSessionService(VoteSessionRepository voteSessionRepository,VoteTopicRepository voteTopicRepository) {
		this.voteSessionRepository = voteSessionRepository;
		this.voteTopicRepository = voteTopicRepository;
	}
	
    // members
    
	// Find all Sessions
    public List<VoteSession> findAll() {
        return voteSessionRepository.findAll();
    }

    // create one session for one vote topic
    public VoteSession createSession(Long id, VoteSession session){
        Optional<VoteTopic> findById = voteTopicRepository.findById(id);
        if(!findById.isPresent()){
            throw new VoteServiceException("Vote topic id was not found.");
        } else {
        	session.setVoteTopicId(findById.get());
        	if (session.getDateTimeToStart() == null) {
                session.setDateTimeToStart(LocalDateTime.now());
            }

            if (session.getDateTimeToEnd() == null) {
                session.setDateTimeToEnd(LocalDateTime.now().plusMinutes(1));
            }
            
            session.setStatus("standby");
            
        	return voteSessionRepository.save(session);
        }
    }

    public VoteSession findById(Long id) {
        Optional<VoteSession> findById = voteSessionRepository.findById(id);
        if(!findById.isPresent()){
            throw new VoteServiceException("Vote session id was not found.");
        } else
        	return findById.get();
    }

    public VoteSession findSessionByVoteTopicId(Long voteTopicId) {
    	Optional <VoteTopic> findVoteTopic = voteTopicRepository.findById(voteTopicId);
    	Optional <VoteSession> findSessionThroughTopicId = voteSessionRepository.findByVoteTopicId(findVoteTopic);

        if(!findSessionThroughTopicId.isPresent()){
            throw new VoteServiceException("Vote topic id was not associated to any vote session.");
        }else
        	return findSessionThroughTopicId.get();
    }
    
    public VoteSession findBySessionIdAndTopicId(Long voteSessionId, Long voteTopicId) {
        Optional<VoteSession> findByIdAndPautaId = voteSessionRepository.findByVoteSessionIdAndVoteTopicId(voteSessionId, voteTopicId);
        if(!findByIdAndPautaId.isPresent()){
            throw new VoteServiceException("Vote session id and vote topic id were not found.");
        }else
        	return findByIdAndPautaId.get();
    }
    
    public List<VoteSession> checkStatus() {
		List<VoteSession> sessionStatus = voteSessionRepository.findAll();
		return sessionStatus;
	}
    
    public VoteSession save(VoteSession vs) {
    	return voteSessionRepository.save(vs);
    }
    
    
}
