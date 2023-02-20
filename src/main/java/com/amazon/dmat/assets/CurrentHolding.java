package com.amazon.dmat.assets;

public class CurrentHolding {
	private int accountNo;
	private int shareId;
	private String shareName;
	private int shareQuantity;
	private float shareBuyPrice;
	
	public CurrentHolding(int accountNo, int shareId, String shareName, int shareQuantity, float shareBuyPrice) {
		super();
		this.accountNo = accountNo;
		this.shareId = shareId;
		this.shareName = shareName;
		this.shareQuantity = shareQuantity;
		this.shareBuyPrice = shareBuyPrice;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public int getShareId() {
		return shareId;
	}

	public String getShareName() {
		return shareName;
	}

	public int getShareQuantity() {
		return shareQuantity;
	}

	public float getShareBuyPrice() {
		return shareBuyPrice;
	}

	
	
	
}
