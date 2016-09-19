/**
 * 
 */
package com.rtstatistics.client.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	public static final String TIMESTAMP_PATTERN_EPOCH_MILLIS = "EPOCH_MILLIS";
	public static final String TIMESTAMP_PATTERN_EPOCH_SECONDS = "EPOCH_SECONDS";

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
	public Statistics setDatasetId(String datasetId) {
		this.datasetId = datasetId;
		return this;
	}
	public String getId() {
		return id;
	}
	public Statistics setId(String id) {
		this.id = id;
		return this;
	}
	public Boolean getIncludeCountDistinct() {
		return includeCountDistinct;
	}
	public Statistics setIncludeCountDistinct(Boolean includeCountDistinct) {
		this.includeCountDistinct = includeCountDistinct;
		return this;
	}
	public String getName() {
		return name;
	}
	public Statistics setName(String name) {
		this.name = name;
		return this;
	}
	public String getPeriodsId() {
		return periodsId;
	}
	public Statistics setPeriodsId(String periodsId) {
		this.periodsId = periodsId;
		return this;
	}
	public String getTimestampFieldFormat() {
		return timestampFieldFormat;
	}
	public Statistics setTimestampFieldFormat(String timestampFieldFormat) {
		this.timestampFieldFormat = timestampFieldFormat;
		return this;
	}
	public String getTimestampFieldName() {
		return timestampFieldName;
	}
	public Statistics setTimestampFieldName(String timestampFieldName) {
		this.timestampFieldName = timestampFieldName;
		return this;
	}
	public String getTimestampFieldZone() {
		return timestampFieldZone;
	}
	public Statistics setTimestampFieldZone(String timestampFieldZone) {
		this.timestampFieldZone = timestampFieldZone;
		return this;
	}
	public String getValueField() {
		return valueField;
	}
	public Statistics setValueField(String valueField) {
		this.valueField = valueField;
		return this;
	}
	public String[][] getKeyFields() {
		return keyFields;
	}
	public Statistics setKeyFields(String[][] keyFields) {
		this.keyFields = keyFields;
		return this;
	}
	
	/**
	 * Set key fields. Use this method when there is no interchangeable key fields.
	 * @param keyFields	all key fields
	 * @return	this
	 */
	@JsonIgnore
	public Statistics setKeyFields(String... keyFields){
		this.keyFields = new String[keyFields.length][];
		for (int i = 0; i < keyFields.length; i ++){
			this.keyFields[i] = new String[]{keyFields[i]};
		}
		return this;
	}

}
