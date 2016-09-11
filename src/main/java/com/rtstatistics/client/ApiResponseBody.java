/**
 * 
 */
package com.rtstatistics.client;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A generic warpper of the response from API endpoints.
 * @author James Hu
 * @param <T> type of the actual response object
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseBody<T> implements ApiErrorInfoProvider{
	
	@JsonIgnoreProperties(ignoreUnknown=true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	static public class ErrorInfo{
		public String type;
		public String message;
		public String incidentId;
		
		public ErrorInfo(){
			
		}
		
		public ErrorInfo(String type, String message, String incidentId){
			this.type = type;
			this.message = message;
			this.incidentId = incidentId;
		}
		
		@Override
		public String toString(){
			return "(" + incidentId + ", " + type + ": " + message + ")";
		}
		
	}
	
	protected ErrorInfo error;
	
	protected T result;
	
	public ApiResponseBody(){
		
	}
	
	public ApiResponseBody(String errorType, String errorMsg){
		this();
		this.error = new ErrorInfo(errorType, errorMsg, null);
	}
	
	public ApiResponseBody(String errorType, String errorMsg, String incidentId){
		this();
		this.error = new ErrorInfo(errorType, errorMsg, incidentId);
	}
	
	public ApiResponseBody(HttpStatus httpStatus, String errorMsg){
		this();
		this.error = new ErrorInfo(httpStatus.getReasonPhrase(), errorMsg, null);
	}

	public ApiResponseBody(T result){
		this();
		this.result = result;
	}
	
	@Override
	public String toString(){
		if (error != null && result == null){
			return error.toString();
		}else if (result != null && error == null){
			return result.toString();
		}else{
			return "(error=" + error + ", result=" + result + ")"; 
		}
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public ErrorInfo getError() {
		return error;
	}

	public void setError(ErrorInfo error) {
		this.error = error;
	}

}
