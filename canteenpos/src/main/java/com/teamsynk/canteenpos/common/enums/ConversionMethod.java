package com.teamsynk.canteenpos.common.enums;

public enum ConversionMethod {
	
	MULTIPLY("Multiply"),
    DIVIDE("Divide");
    
private final String label;
	
	ConversionMethod(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
