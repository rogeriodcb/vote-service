package com.voteservice.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voteservice.model.VoteSession;
import com.voteservice.model.VoteTopic;

/**
* <p>**************************************************************************************
* <p>                                 VoteSessionRepository
* <p>**************************************************************************************
* <p>
* Provide JPA access management to the databank session table. Includes some other queries.
*/
@Repository
public interface VoteSessionRepository extends JpaRepository <VoteSession,Long> {
	
    Optional<VoteSession> findByVoteSessionIdAndVoteTopicId(Long voteSessionId, Long voteTopicId);
    
	List<VoteSession> findAll();

    
    Long countByVoteTopicId(Long voteTopicId);
    
    Optional <VoteSession> findByVoteTopicId(Optional <VoteTopic> voteTopic);
}
