package com.voteservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime dateTimeToStart;
	
	// for this case, if the date time to end was not passed assumes 1 minute
	@Column(nullable = true)
	private LocalDateTime dateTimeToEnd;
	
	@OneToOne
	@JoinColumn(name="vote_topic")
	private VoteTopic voteTopic;
	
}
