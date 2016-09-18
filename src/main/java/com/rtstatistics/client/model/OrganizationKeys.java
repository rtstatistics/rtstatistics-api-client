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
public class OrganizationKeys implements Serializable{
	private static final long serialVersionUID = 5994066182799062872L;

	protected String[] apiKeys;
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String[] getApiKeys() {
		return apiKeys;
	}
	
	public void setApiKeys(String[] apiKeys) {
		this.apiKeys = apiKeys;
	}
}
