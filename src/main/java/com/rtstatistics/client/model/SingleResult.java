/**
 * 
 */
package com.rtstatistics.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author James Hu (Zhengmao Hu)
 * @param <T> type of the result
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleResult<T> implements Serializable{
	private static final long serialVersionUID = -1968733613689456403L;

	protected T result;

	public SingleResult() {
	}
	
	public SingleResult(T result){
		this.result = result;
	}
	
	@Override
	public String toString(){
		return "(result: " + result == null ? "null" : result.toString() + ")";
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	

}
