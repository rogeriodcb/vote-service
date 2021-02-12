package com.voteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voteservice.entity.VoteSession;

public interface VoteSessionRepository extends JpaRepository <VoteSession,Long> {

}
