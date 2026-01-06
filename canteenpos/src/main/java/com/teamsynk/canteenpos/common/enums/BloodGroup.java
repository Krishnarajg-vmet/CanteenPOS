package com.teamsynk.canteenpos.common.enums;

public enum BloodGroup {
	
	O_POS("O +ve"),
	O_NEG("O -ve"),
	A_POS("A +ve"),
	A_NEG("A -ve"),
	B_POS("B +ve"),
	B_NEG("B -ve"),
	AB_POS("AB +ve"),
	AB_NEG("AB -ve"),
	UNKNOWN("Unknown");
	
	private final String label;
	
	BloodGroup(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
