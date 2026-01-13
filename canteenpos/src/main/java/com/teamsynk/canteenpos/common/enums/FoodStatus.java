package com.teamsynk.canteenpos.common.enums;

public enum FoodStatus {

	DRAFT("Item created but not approved for sale"),
	ACTIVE("Item approved and sellable at locations"),
	SUSPENDED("Item temporarily unavailable for sale"),
	RETIRED("Item permanently decommissioned, historical records preserved");
	
	private final String label;
	
	FoodStatus(String label) {
        this.label = label;
    }
	
	public String getLabel() {
        return label;
    }

}
