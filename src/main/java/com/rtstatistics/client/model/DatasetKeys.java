/**
 * 
 */
package com.rtstatistics.client.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author James Hu (Zhengmao Hu)
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatasetKeys implements Serializable{
	private static final long serialVersionUID = 3671529926181673152L;

	protected String[] queryKeys;
	protected String[] sendKeys;
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String[] getQueryKeys() {
		return queryKeys;
	}
	public void setQueryKeys(String[] queryKeys) {
		this.queryKeys = queryKeys;
	}
	public String[] getSendKeys() {
		return sendKeys;
	}
	public void setSendKeys(String[] sendKeys) {
		this.sendKeys = sendKeys;
	}
}
