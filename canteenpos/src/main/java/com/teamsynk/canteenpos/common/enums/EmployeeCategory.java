package com.teamsynk.canteenpos.common.enums;

public enum EmployeeCategory {
	
	CASHIER("Cashier"),
	BILLING("Billing"),
	STAFF("Staff");
	
	private final String label;
	
	EmployeeCategory(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
