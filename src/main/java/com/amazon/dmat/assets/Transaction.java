package com.amazon.dmat.assets;


/*
 * transactionId	: Transaction Id	
 * tType			: Buy or sell
 * shareId			: Id of Share(Foreign key)
 * shareName		: Name of Share
 * tSharePrice		: at which price share was bought or sold
 * tShareQuantity	: Number of shares that were transacted
 * tCharge			: The charge on transaction
 * tFinalAmount	: Final Amount of Transaction
 * transactionTime	: Data & time the share was bought or sold				
 * accountNo		: Account number of user(Foreign key)
*/


public class Transaction {
	private int transactionId;	
	private String tType;
	private int shareId;
	private String shareName;
	private float tSharePrice;
	private int tShareQuantity;
	private float tCharge;
	private float tFinalAmount;
	private int transactionTime;
	private int accountNo;
	
	public Transaction(int transactionId, String tType, int shareId, String shareName, float tSharePrice,
			int tShareQuantity, float tCharge, float tFinalAmount, int transactionTime, int accountNo) {
		super();
		this.transactionId = transactionId;
		this.tType = tType;
		this.shareId = shareId;
		this.shareName = shareName;
		this.tSharePrice = tSharePrice;
		this.tShareQuantity = tShareQuantity;
		this.tCharge = tCharge;
		this.tFinalAmount = tFinalAmount;
		this.transactionTime = transactionTime;
		this.accountNo = accountNo;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public String gettType() {
		return tType;
	}

	public int getShareId() {
		return shareId;
	}

	public String getShareName() {
		return shareName;
	}

	public float gettSharePrice() {
		return tSharePrice;
	}

	public int gettShareQuantity() {
		return tShareQuantity;
	}

	public float gettCharge() {
		return tCharge;
	}

	public float gettFinalAmount() {
		return tFinalAmount;
	}

	public int getTransactionTime() {
		return transactionTime;
	}

	public int getAccountNo() {
		return accountNo;
	}

	
}
