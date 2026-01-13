package com.teamsynk.canteenpos.common.enums;

public enum UomType {
	
	COUNT("Count"),
    WEIGHT("Weight"),
    VOLUME("Volume");
	
	private final String label;
	
	UomType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
