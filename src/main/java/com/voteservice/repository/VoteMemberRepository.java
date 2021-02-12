package com.voteservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voteservice.entity.VoteMember;

public interface VoteMemberRepository extends JpaRepository <VoteMember,Long>{

}
