/**
 * 
 */
package com.rtstatistics.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * The definition of a statistics period/stage.
 * @author James Hu
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsPeriod implements Serializable{
	private static final long serialVersionUID = 5547364088313544968L;

	protected Integer amount;
	
	protected String unit;
	
	protected String timeZone;
	
	protected Configuration configuration;
	
	protected List<StatisticsPeriod> upperLevelPeriods;
	
	static public class Configuration implements Serializable{
		private static final long serialVersionUID = -6543053573180772985L;

		protected Integer keepWithinMinutes;
		
		public Configuration(){
		}
		
		public Configuration(int keepWithinMinutes){
			this.keepWithinMinutes = keepWithinMinutes;
		}
		
		public Integer getKeepWithinMinutes() {
			return keepWithinMinutes;
		}

		public void setKeepWithinMinutes(Integer keepWithinMinutes) {
			this.keepWithinMinutes = keepWithinMinutes;
		}
	}
	
	public StatisticsPeriod(){
		
	}
	
	public StatisticsPeriod(String timeZone, int amount, String unit, int keepWithinMinutes){
		this.timeZone = timeZone;
		this.amount = amount;
		this.unit = unit;
		this.configuration = new Configuration(keepWithinMinutes);
	}
	
	public void addUpperLevelPeriod(StatisticsPeriod upperLevelPeriod) {
		if (upperLevelPeriods == null){
			upperLevelPeriods = new ArrayList<StatisticsPeriod>();
		}
		upperLevelPeriods.add(upperLevelPeriod);
	}


	
	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return the upperLevelPeriods
	 */
	public List<StatisticsPeriod> getUpperLevelPeriods() {
		return upperLevelPeriods;
	}

	/**
	 * @param upperLevelPeriods the upperLevelPeriods to set
	 */
	public void setUpperLevelPeriods(List<StatisticsPeriod> upperLevelPeriods) {
		this.upperLevelPeriods = upperLevelPeriods;
	}

	
}
