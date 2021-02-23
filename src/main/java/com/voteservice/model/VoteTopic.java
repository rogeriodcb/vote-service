package com.voteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>**************************************************************************************
* <p>                                VoteTopic
* <p>**************************************************************************************
* <p>
* This model is responsible to create a vote Topic table modeling for the data bank. 
* It includes field validation, table name and column names.
* <p> Lombok is used to provide builder, getters and setters for this class.
* <p> To comply with the requirements:
* <li> vote topic shall be created
*/
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote_topic")
public class VoteTopic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vote_topic_id")
	private Long voteTopicId;
	
	@Column(name="name",nullable=false, unique=true)
	@NotBlank(message="The vote topic name is required")
	@Size(max=200)
	private String name;
	
	@Column(name="description",nullable=false)
	private String description;
	

}
