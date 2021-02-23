package com.voteservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voteservice.model.VoteMember;
import com.voteservice.model.VoteTopic;

/**
* <p>**************************************************************************************
* <p>                                 VoteMemberRepository
* <p>**************************************************************************************
* <p>
* Provide JPA access management to the databank member table. Includes some other queries.
*/
@Repository
public interface VoteMemberRepository extends JpaRepository <VoteMember,Long>{
    Optional<VoteMember> findByCpf(String cpf);

    Optional<List<VoteMember>> findByVoteTopic(VoteTopic voteTopic);

    Long countByVoteTopicAndVoteTrue(VoteTopic voteTopic);
    
    Long countByVoteTopicAndVoteFalse(VoteTopic voteTopic);

    Optional<VoteMember> findByCpfAndVoteTopic(String cpf, VoteTopic voteTopic);
}
