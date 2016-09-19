package com.rtstatistics.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NativeField extends Field{
	private static final long serialVersionUID = 3871888829001841379L;

	protected String path;

	public NativeField(){
		super();
	}

	public NativeField(String name) {
		super(name);
	}

	public NativeField(String name, String path) {
		super(name);
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}