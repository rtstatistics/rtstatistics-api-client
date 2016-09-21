/**
 * 
 */
package com.rtstatistics.client.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	
	public static final String TIMESTAMP_PATTERN_EPOCH_MILLIS = "EPOCH_MILLIS";
	public static final String TIMESTAMP_PATTERN_EPOCH_SECONDS = "EPOCH_SECONDS";

	protected String id;
	protected String name;
	protected String datasetId;
	protected String periodsId;
	protected Boolean includeCountDistinct;
	protected String valueField;
	protected Object[] keyFields;
	protected String timestampFieldFormat;
	protected String timestampFieldName;
	protected String timestampFieldZone;

	/**
	 * Ensure that key fields is not an empty array (convert to null),
	 * and does not contain Collections (convert to array). The normalized key fields 
	 * will be null, or Object array containing possibly null, String, and String[].
	 * @param keyFields		the key fields array to be normalized 
	 * @return The normalized key fields that may be null, or Object array containing possibly null, String, and String[].
	 */
	static public Object[] normalizeKeyFields(Object[] keyFields){
		if (keyFields != null){
			if (keyFields.length == 0){
				keyFields = null;
			}else{
				for (int i = 0; i < keyFields.length; i ++){
					Object o = keyFields[i];
					if (o != null && !(o instanceof String) && !(o instanceof String[])){
						if (o instanceof Collection){
							Collection<?> c = (Collection<?>) o;
							String[] o2 = new String[c.size()];
							int j = 0;
							for (Object e: c){
								o2[j++] = e == null? null : e.toString();
							}
							keyFields[i] = o2;
						}else if (o.getClass().isArray()){
							Object[] c = (Object[]) o;
							String[] o2 = new String[c.length];
							int j = 0;
							for (Object e: c){
								o2[j++] = e == null? null : e.toString();
							}
							keyFields[i] = o2;
						}else{
							keyFields[i] = o.toString();
						}
					}
				}
			}
		}
		return keyFields;
	}

	/**
	 * Sort key fields in asc order
	 * @param keyFields		the key fields array to be sorted 
	 */
	@SuppressWarnings("unchecked")
	static public void sortKeyFields(Object[] keyFields){
		if (keyFields != null){
			synchronized (keyFields){
				Arrays.sort(keyFields, new Comparator<Object>(){
					@Override
					public int compare(Object o1, Object o2){
						// assume there is no null
						String s1;
						String s2;
						
						if (o1 instanceof String[]){
							Arrays.sort((String[])o1);
							s1 = StringUtils.join((String[])o1);
						}else if (o1 instanceof List){
							Collections.sort((List)o1);
							s1 = StringUtils.join(((List)o1).iterator(), "");
						}else{
							s1 = o1.toString();
						}
						
						if (o2 instanceof String[]){
							Arrays.sort((String[])o2);
							s2 = StringUtils.join((String[])o2);
						}else if (o2 instanceof List){
							Collections.sort((List)o2);
							s2 = StringUtils.join(((List)o2).iterator(), "");
						}else{
							s2 = o2.toString();
						}
						
						return s1.compareTo(s2);
					}
				});
			}
		}
	}

	/**
	 * Make sure that keyFields can only be null, or an array containing possibly null, String, or String[].
	 */
	public void normalizeAndSortKeyFields(){
		keyFields = normalizeKeyFields(keyFields);
		sortKeyFields(keyFields);
	}
	
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
	public Object[] getKeyFields() {
		return keyFields;
	}
	public Statistics setKeyFields(Object... keyFields) {
		this.keyFields = keyFields;
		return this;
	}
	
}
