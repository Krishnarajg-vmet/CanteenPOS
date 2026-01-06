package com.teamsynk.canteenpos.common.enums;

public enum PaymentMode {
	
	CASH(1, "Cash"),
	CARD(2, "Card"),
	UPI(3, "UPI");
	
	private final int id;
	private final String label;
	
	PaymentMode(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}

}
