package com.voteservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/** 
* <p>**************************************************************************************
* <p>                                <b>Vote Service</b>
* <p>**************************************************************************************
* <p>
* This back-end application provides a simple vote service following the requirements:
* <p>
* <p>1) Register a new agenda;
* <p>2) Open a voting session on an agenda (the voting session must be open for a while
*    determined in the opening call or 1 minute by default);
* <p>3) Receive votes from members on agendas (votes are only 'Yes' / 'No'. Each member is
*    identified by a unique id and can vote only once per agenda);
* <p>4) Count the votes and give the result of the vote on the agenda.
* <p>5) Realize request as GET https://user-info.herokuapp.com/users/{cpf}
*    and verify if the CPF is invalid and verify if Othe CPF is valid, 
*    the API will return if the user can (ABLE_TO_VOTE) or not
*    can (UNABLE_TO_VOTE) perform the operation 
* <p>6) The voting result needs to be informed for the rest of the platform, 
*    this must be preferably done through messaging. When the voting session closes, post
*    a message with the result of the vote
* <p>7) The solution must be executed in the cloud through an API REST
* <p>
* This application was developed with:
* <p><li>[Springboot] The web framework used
* <li>[Lombok] The build tools framework
* <li>[Hibernate] The Object/Relational Mapping (ORM) framework
* <li>[Maven] Dependency Management
* <li>[PostgreSQL] Data bank to persist the data like vote topic, sessions and voting
* <li>[Swagger] The API design framework allowing user to test through web this vote service
* <li>[RabbitMq] The message service  
* <li>[Jackson] High-performance JSON processor API for Java
* @author - Rogerio de C.B.
*/
@EnableScheduling
@SpringBootApplication
public class VoteApplication {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
	}

}
