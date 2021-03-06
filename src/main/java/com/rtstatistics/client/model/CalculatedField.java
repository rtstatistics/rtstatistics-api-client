package com.rtstatistics.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculatedField extends Field{
	private static final long serialVersionUID = -2184812383965762236L;

	protected String formula;
	
	public CalculatedField(){
		super();
	}

	public CalculatedField(String name) {
		super(name);
	}

	public CalculatedField(String name, String formula) {
		super(name);
		this.formula = formula;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
}