package com.gcd.service.exception;


public class GCDException extends Exception{
	
	private String code;
	private String system;
	private String type;
	private String description;
	
	public GCDException() {
		super();
	}	
	
	public GCDException(String code, String system, String type, String description) {
		super(description);
		this.code = code;
		this.system = system;
		this.type = type;
		this.description = description;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
