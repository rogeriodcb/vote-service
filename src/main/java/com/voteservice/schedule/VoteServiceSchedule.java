package com.voteservice.schedule;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.voteservice.dto.MessageQueueDTO;
import com.voteservice.dto.VoteDTO;
import com.voteservice.model.VoteSession;
import com.voteservice.service.AmqpService;
import com.voteservice.service.VoteMemberService;
import com.voteservice.service.VoteSessionService;

/**
* <p>**************************************************************************************
* <p>                                 VoteServiceSchedule
* <p>**************************************************************************************
* <p>
* This particular schedule is responsible to analyze each vote session and verify if their
* status is standby (waiting for voting starts), opened (able to vote) and closed (no more votes 
* is allowed)
* <p>Right after the session is closed a result message is sent to the message queue and 
* report to the platform (in this case, posting a output string)
*/
@Component
public class VoteServiceSchedule {
	private VoteSessionService sessionService;
	private VoteMemberService voteMemberService;
	private AmqpService amqpService;
	
	// constructor
	@Autowired
	public VoteServiceSchedule(VoteSessionService sessionService,VoteMemberService voteMemberService, AmqpService amqpService) {
		this.sessionService = sessionService;
		this.voteMemberService = voteMemberService;
		this.amqpService = amqpService;
		
	}

	@Scheduled(cron = "0/30 * * * * *")
	public void checkEachSession() {
		
		System.out.println("Checking sessions...");
		// Opened
		
		// get actual date and time
		LocalDateTime dt = LocalDateTime.now();
		
		for(VoteSession vs: sessionService.checkStatus()) {
			// verify if the session shall be opened
			if((vs.getStatus().equals("standby")) && (vs.getDateTimeToStart().isBefore(dt)) && (vs.getDateTimeToEnd().isAfter(dt))) {

				vs.setStatus("opened");
				sessionService.save(vs);
			}
			// verify if the session shall be closed
			else if((vs.getStatus().equals("opened")) && (vs.getDateTimeToEnd().isBefore(dt))) {

				vs.setStatus("closed");
				sessionService.save(vs);
				// send message to the consumer
				VoteDTO voteDTO = new VoteDTO(vs.getVoteTopicId(), 
						vs,
						voteMemberService.countVoteTrue(vs.getVoteTopicId()),
						voteMemberService.countVoteFalse(vs.getVoteTopicId()));
				MessageQueueDTO msg = new MessageQueueDTO();
				msg.setText("======Voting finished======\n\n"+voteDTO.getMessage()+"===========================\n\n");
				amqpService.sendToConsumer(msg);				
			}
			// in case of the validation cannot pass to opened and end datetime is done then close without send message (no voting occurred)
			else if((vs.getStatus().equals("standby")) && (vs.getDateTimeToEnd().isBefore(dt)) ){

				vs.setStatus("closed");
				sessionService.save(vs);
	
			}

		
		}
		System.out.println("end of check.");

	}
}
