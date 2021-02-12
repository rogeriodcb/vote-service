package com.voteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voteservice.entity.VoteTopic;

public interface VoteTopicRepository extends JpaRepository <VoteTopic, Long> {

}
