package com.teamsynk.canteenpos.common.enums;

public enum Title {
	
	MR("Mr"),
	MRS("Mrs"),
	MS("Ms"),
	MISS("Miss"),
	DR("Dr");
	
	private final String label;
	
	Title(String label) {
		this.label = label;
	}
	
	public String getLabel() {
	    return label;
	}

}
