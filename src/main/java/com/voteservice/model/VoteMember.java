package com.voteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* <p>**************************************************************************************
* <p>                                VoteMember
* <p>**************************************************************************************
* <p>
* This model is responsible to create a Member table modeling for the data bank. It includes
* field validation, table name and column names.
* <p> Lombok is used to provide builder, getters and setters for this class.
* <p> To comply with the requirements:
* <li> CPF document shall be provided
* <li> vote shall be yes or no (true or false)
* <li> many members, with different valid and able CPFs, shall be vote in a 
*      opened session. Once per vote topic.
* <li> The same CPF can be vote in a different vote topic but not in the same
*/
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote_member")
public class VoteMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vote_member_id")
	private Long voteMemberId;
	
	@Column(name="cpf",nullable = false)
	@NotBlank(message="The CPF shall be passed")
	private String cpf;
	
	@Column(nullable = false)
	private boolean vote;
	
	@ManyToOne
	@JoinColumn(name="vote_topic_id")
	@NotNull(message="The vote topic ID shall be assigned")
	private VoteTopic voteTopic;
}
