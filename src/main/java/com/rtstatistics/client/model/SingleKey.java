/**
 * 
 */
package com.rtstatistics.client.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author James Hu (Zhengmao Hu)
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleKey implements Serializable{
	private static final long serialVersionUID = 3603746618798635267L;

	protected String key;
	
	public SingleKey(){
		
	}
	
	public SingleKey(String key){
		this.key = key;
	}

	@Override
	public String toString(){
		return "(key: " + key + ")";
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
