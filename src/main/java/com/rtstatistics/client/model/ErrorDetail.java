package com.rtstatistics.client.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Detailed information about the error.
 * @author James Hu (Zhengmao Hu)
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail implements Serializable{
	private static final long serialVersionUID = -6822897125753645347L;

	protected String type;
	protected String message;
	protected String incidentId;
	
	public ErrorDetail(){
		
	}
	
	public ErrorDetail(String type, String message, String incidentId){
		this.type = type;
		this.message = message;
		this.incidentId = incidentId;
	}
	
	public ErrorDetail(HttpStatus httpStatus){
		this(String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase(), null);
	}
	
	@Override
	public String toString(){
		return "(" + incidentId + ", " + type + ": " + message + ")";
	}
	
}