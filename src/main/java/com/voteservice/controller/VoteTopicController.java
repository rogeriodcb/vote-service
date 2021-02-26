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

import com.voteservice.dto.MessageResponseDTO;
import com.voteservice.model.VoteTopic;
import com.voteservice.service.VoteTopicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>**************************************************************************************
* <p>                                 VoteTopicController
* <p>**************************************************************************************
* <p>
* This Rest API controller provides vote topic creation and information gathering.
* <p> To comply with the requirement the following implementations were made:
* <li> create a vote topic
* <li> the deletion was not implemented. The data shall be persisted and not deleted
* <li> get information about vote topic created
*/
@RestController
@RequestMapping(value="/api/v1")
@Api(value="API REST Cooperative vote service - Vote Topic")
@CrossOrigin(origins="*")
public class VoteTopicController {
	
	private VoteTopicService voteTopicService;

	// constructor
	@Autowired
	public VoteTopicController(VoteTopicService voteTopicService) {
		this.voteTopicService = voteTopicService;
	}
	
	// Rest mappings

	@GetMapping("/votetopics")
	@ApiOperation(value="This method returns all vote topics registered.")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VoteTopic> all() {
		return voteTopicService.findAll();
	}
	
	@PostMapping("/votetopic")
	@ApiOperation(value="This method creates new vote topic.")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDTO create(@RequestBody @Valid VoteTopic voteTopic) {
		return voteTopicService.save(voteTopic);		
	}
 
	@GetMapping("/votetopic/{id}")
	@ApiOperation(value="This method find specific vote topic through the vote topic id.")
	@ResponseStatus(code = HttpStatus.OK)
	public VoteTopic findById(@PathVariable Long id) {
		return voteTopicService.findById(id);
	}


	
	

}
