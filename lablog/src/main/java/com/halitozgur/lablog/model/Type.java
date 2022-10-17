package com.halitozgur.lablog.model;

public enum Type {
	
	DELIVERY("Delivery"), PLACEMENT("Placement"), CLEAN("Clean"), CHARGE("Charge");

	private String type;
		
	Type(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
