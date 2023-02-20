package com.amazon.dmat.assets;


/*
 * shareId		: Id of Share(Unique Id)
 * shareName	: Name of Share
 * sharePrice	: Price of Share
 */

public class Share {
	private int shareId;
	private String shareName;
	private float sharePrice;
	
	public Share(int shareId, String shareName, float sharePrice) {
		super();
		this.shareId = shareId;
		this.shareName = shareName;
		this.sharePrice = sharePrice;
	}

	public int getShareId() {
		return shareId;
	}

	public String getShareName() {
		return shareName;
	}

	public float getSharePrice() {
		return sharePrice;
	}
	
	
	
}
