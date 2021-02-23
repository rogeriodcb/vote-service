package com.voteservice.dto;

import java.time.format.DateTimeFormatter;

import com.voteservice.model.VoteSession;
import com.voteservice.model.VoteTopic;

import lombok.Builder;
import lombok.Data;

/**
* <p>**************************************************************************************
* <p>                                VoteDTO
* <p>**************************************************************************************
* <p>
* Data Transfer Object to be used in the Message Result
*/
@Data
@Builder
public class VoteDTO {
	private VoteTopic voteTopic;
	private VoteSession voteSession;
	private Long voteYes;
	private Long voteNo;
	
	public VoteDTO(VoteTopic voteTopic, VoteSession voteSession, Long voteYes, Long voteNo) {
		super();
		this.voteTopic = voteTopic;
		this.voteSession = voteSession;
		this.voteYes = voteYes;
		this.voteNo = voteNo;
	}
	
	public String getMessage() {
		return String.format("Vote Topic.: %s\n"
							+"Description: %s\n"
				 		    +"Vote session started at.: %s\n"
							+"Vote session finished at: %s\n"
				 		    +"Results:\n"
							+"Total members that voted: %s\n"
							+"Members voted yes.......: %s\n"
				 		    +"Members voted no........: %s\n\n",
				 		    	voteTopic.getName(),
				 		    	voteTopic.getDescription(),
				 		    	voteSession.getDateTimeToStart().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
				 		    	voteSession.getDateTimeToEnd().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
				 		    	voteYes+voteNo,
				 		    	voteYes,
				 		    	voteNo);

	}


	
}
