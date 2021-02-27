package com.voteservice.controller;

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

import com.voteservice.model.VoteMember;
import com.voteservice.service.VoteMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>**************************************************************************************
* <p>                                 VoteMemberController
* <p>**************************************************************************************
* <p>
* This Rest API controller provides member creation and information gathering.
* <p> To comply with the requirement the following implementations were made:
* <li> create a member with valid CPF
* <li> the CPF is check for validation and able or not to vote
* <li> the CPF shall be unique for each vote topic
*/
@RestController
@RequestMapping(value="/voteservice-api/v1")
@Api(value="API REST Cooperative vote service - Vote Member")
@CrossOrigin(origins="*")
public class VoteMemberController {
	
	private VoteMemberService voteMemberService;

	// constructor
	@Autowired
	public VoteMemberController(VoteMemberService voteMemberService) {
		this.voteMemberService = voteMemberService;
	}
		
	
	// Rest mappings

	// create vote
	@PostMapping("/votetopic/{id}/votemember")
	@ApiOperation(value="This method creates new vote.")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VoteMember createVote(@PathVariable Long id, @RequestBody VoteMember voteMember) {
		return voteMemberService.createVote(id, voteMember);
	} 


	@GetMapping("/votemember/{id}")
	@ApiOperation(value="This method finds vote through vote member id.")
	@ResponseStatus(code = HttpStatus.OK)
	public VoteMember findVoteById(@PathVariable Long id) {
		return voteMemberService.findById(id);
	}
	




}
