package com.teamsynk.canteenpos.common.enums;

public enum StockTransactionType {
	
	PURCHASE(1, "Purchased"),
	SALE(2, "SALE"),
	ADJUSTMENT(3, "Adjustment"),
	WASTAGE(4, "Wastage"),
	RETURN(5, "Return"),
	TRANSFER(6, "Transfer");
	
	private final int id;
	
	private final String label;
	
	StockTransactionType(int id, String label) {
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
