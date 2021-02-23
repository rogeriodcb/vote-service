package com.voteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voteservice.model.VoteTopic;

/**
* <p>**************************************************************************************
* <p>                                 VoteTopicRepository
* <p>**************************************************************************************
* <p>
* Provide JPA access management to the databank vote topic table.
*/
@Repository
public interface VoteTopicRepository extends JpaRepository <VoteTopic, Long> {

}
