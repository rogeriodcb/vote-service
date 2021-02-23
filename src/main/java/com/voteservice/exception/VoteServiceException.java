package com.voteservice.exception;

import org.springframework.web.client.RestClientException;
/**
* <p>**************************************************************************************
* <p>                                 VoteServiceException
* <p>**************************************************************************************
* <p>
* Provide Rest Client general exception. Used in some parts of this project to give a
* error message to the user.
*/
public class VoteServiceException extends RestClientException {

	private static final long serialVersionUID = 5553707156721755355L;

	public VoteServiceException(String msg) {
		super(msg);
	}
	

}
