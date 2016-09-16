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
 * Summary information of the statistics definition.
 * @author James Hu (Zhengmao Hu)
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Statistics implements Serializable{
	private static final long serialVersionUID = -1571952011399853165L;

	protected String id;
	protected String name;
	protected String datasetId;
	protected String periodsId;
	protected Boolean includeCountDistinct;
	protected String valueField;
	protected String[][] keyFields;
	protected String timestampFieldFormat;
	protected String timestampFieldName;
	protected String timestampFieldZone;

	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getDatasetId() {
		return datasetId;
	}
	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIncludeCountDistinct() {
		return includeCountDistinct;
	}
	public void setIncludeCountDistinct(Boolean includeCountDistinct) {
		this.includeCountDistinct = includeCountDistinct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeriodsId() {
		return periodsId;
	}
	public void setPeriodsId(String periodsId) {
		this.periodsId = periodsId;
	}
	public String getTimestampFieldFormat() {
		return timestampFieldFormat;
	}
	public void setTimestampFieldFormat(String timestampFieldFormat) {
		this.timestampFieldFormat = timestampFieldFormat;
	}
	public String getTimestampFieldName() {
		return timestampFieldName;
	}
	public void setTimestampFieldName(String timestampFieldName) {
		this.timestampFieldName = timestampFieldName;
	}
	public String getTimestampFieldZone() {
		return timestampFieldZone;
	}
	public void setTimestampFieldZone(String timestampFieldZone) {
		this.timestampFieldZone = timestampFieldZone;
	}
	public String getValueField() {
		return valueField;
	}
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	public String[][] getKeyFields() {
		return keyFields;
	}
	public void setKeyFields(String[][] keyFields) {
		this.keyFields = keyFields;
	}

}
