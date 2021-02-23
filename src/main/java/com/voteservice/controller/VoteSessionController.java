package com.voteservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.voteservice.model.VoteSession;
import com.voteservice.service.VoteSessionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>**************************************************************************************
* <p>                                 VoteSessionController
* <p>**************************************************************************************
* <p>
* This Rest API controller provides session creation and information gathering.
* <p> To comply with the requirement the following implementations were made:
* <li> create a session for one voting topic
* <li> the deletion was not implemented. The data shall be persisted and not deleted
* <li> get information about session created
*/
@RestController
@RequestMapping(value="/api/v1")
@Api(value="API REST Cooperative vote service - Vote Session")
@CrossOrigin(origins="*")
public class VoteSessionController {
	
	private VoteSessionService voteSessionService;

	// constructor
	@Autowired
	public VoteSessionController(VoteSessionService voteSessionService) {
		this.voteSessionService = voteSessionService;
	}
	
	// Rest mappings
	
	@GetMapping("/Sessions")
	@ApiOperation(value="This method returns all vote sessions registered.")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VoteSession> all() {
		return voteSessionService.findAll();
	}

	@PostMapping("/votetopic/{id}/session")
	@ApiOperation(value="This method create a session for one vote topic id.")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VoteSession createSession(@PathVariable Long id, @RequestBody @Valid VoteSession voteSession) {
		return voteSessionService.createSession(id,voteSession);
	}

	@GetMapping("votesession/{id}")
	@ApiOperation(value="This method find a session through session id.")
	@ResponseStatus(code = HttpStatus.OK)
	public VoteSession findSessionById(@PathVariable Long id) {
		return voteSessionService.findById(id);
	}

	@GetMapping("votetopic/{id}/session")
	@ApiOperation(value="This method find a session through vote topic id.")
	@ResponseStatus(code = HttpStatus.OK)
	public VoteSession findSessionByVoteTopicId(@PathVariable Long id) {
		return voteSessionService.findSessionByVoteTopicId(id);
	}
	
	
}
