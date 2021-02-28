package com.voteservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>**************************************************************************************
* <p>                                VoteSession
* <p>**************************************************************************************
* <p>
* This model is responsible to create a Session table modeling for the data bank. It includes
* field validation, table name and column names.
* <p> Lombok is used to provide builder, getters and setters for this class.
* <p> To comply with the requirements:
* <li> Start date and time shall be provided. If not a current date and time will be assigned
* <li> End date and time shall be provided. If not a current date and time, plus 1 minute, will be assigned
* <li> Session status indicates if the session:
* <p>a) Standby - session is waiting to open
* <p>b) Opened  - session opened to vote
* <p>c) Closed  - session is closed (in this case a result will be provided)
* <li> This session is attached to one vote topic (required)
* 
* <p><p> Note: Some implementation will be found in services and schedule folder
*/
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote_session")
public class VoteSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="session_id")
	private Long voteSessionId;
	
	@Column(name="date_time_start", nullable = false)
	@NotNull(message="The start Date and Time shall be assigned")
	private LocalDateTime dateTimeToStart;
	
	// for this case, if the date time to end was not passed assumes 1 minute
	@Column(name="date_time_end",nullable = true)
	@NotNull(message="The end Date and Time shall be assigned")
	private LocalDateTime dateTimeToEnd;
	
	// for this case, if the date time to end was not passed assumes 1 minute
	@Column(name="session_status",nullable = true)
	@NotNull(message="The status shall be assigned")
	private String status;
		
	
	@OneToOne
	@JoinColumn(name="vote_topic_id")
	@NotNull(message="The vote topic ID shall be assigned")
	private VoteTopic voteTopicId;
	
}
