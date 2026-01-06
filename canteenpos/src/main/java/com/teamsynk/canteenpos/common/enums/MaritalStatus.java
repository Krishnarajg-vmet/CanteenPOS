package com.teamsynk.canteenpos.common.enums;

public enum MaritalStatus {

	MARRIED("Married"),
	SINGLE("Single");
	
	private final String label;
	
	MaritalStatus(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
}
