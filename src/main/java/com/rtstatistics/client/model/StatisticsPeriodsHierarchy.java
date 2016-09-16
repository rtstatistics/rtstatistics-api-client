/**
 * 
 */
package com.rtstatistics.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration of hierarchical statistics periods
 * @author James Hu
 *
 */
public class StatisticsPeriodsHierarchy implements Serializable {
	private static final long serialVersionUID = -6916127078836380064L;

	protected String id;
	protected String name;
	protected List<StatisticsPeriod> periods;

	public StatisticsPeriodsHierarchy(){
		
	}
	
	public StatisticsPeriodsHierarchy(String name){
		this.name = name;
	}
	
	public void addStatisticsPeriod(StatisticsPeriod statisticsPeriod){
		if (periods == null){
			periods = new ArrayList<StatisticsPeriod>();
		}
		
		periods.add(statisticsPeriod);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the periods
	 */
	public List<StatisticsPeriod> getPeriods() {
		return periods;
	}

	/**
	 * @param periods the periods to set
	 */
	public void setPeriods(List<StatisticsPeriod> periods) {
		this.periods = periods;
	}
	
}
