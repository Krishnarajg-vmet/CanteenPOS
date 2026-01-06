package com.teamsynk.canteenpos.common.enums;

public enum EmployeeType {
	
	PERMANENT("Permanent"),
	FTC("FTC"),
	CONTRACT("Contract");

	private final String label;
	
	EmployeeType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
