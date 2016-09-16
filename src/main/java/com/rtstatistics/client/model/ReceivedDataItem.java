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
public class ReceivedDataItem implements Serializable{
	private static final long serialVersionUID = -8311219343444854879L;

	protected String dataString;
	protected String receipt;
	protected Long receivedTime;
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getDataString() {
		return dataString;
	}
	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	
	/**
	 * Get received time as number of milliseconds since Epoch
	 * @return	number of milliseconds since Epoch that the data item was received by api.rtstatistics.com
	 */
	public Long getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(Long receivedTime) {
		this.receivedTime = receivedTime;
	}
	
	
}
