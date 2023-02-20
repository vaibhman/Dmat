package com.amazon.dmat.assets;


/*
 * transactionId	: Transaction Id
 * shareId			: Id of Share(Foreign key)
 * accountNo		: Account number of user(Foreign key)
 * transactionTime	: Data & time the share was bought or sold
 * tShareQuantity	: Number of shares that were transacted
 * tSharePrice		: at which price share was bought or sold
 * tCharge			: The charge on transaction
 * tFinalAmount	: Final Amount of Transaction
 * tType			: Buy or sell
*/


public class Transaction {
	private int transactionId;	
	private int shareId;
	private int accountNo;
	private int transactionTime;
	private int tShareQuantity;
	private float tSharePrice;
	private float tCharge;
	private float tFinalAmount;
	private String tType;
	
	public Transaction(int transactionId, int shareId, int accountNo, int transactionTime, int tShareQuantity,
			float tSharePrice, float tCharge, float tFinalAmount, String tType) {
		super();
		this.transactionId = transactionId;
		this.shareId = shareId;
		this.accountNo = accountNo;
		this.transactionTime = transactionTime;
		this.tShareQuantity = tShareQuantity;
		this.tSharePrice = tSharePrice;
		this.tCharge = tCharge;
		this.tFinalAmount = tFinalAmount;
		this.tType = tType;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public int getShareId() {
		return shareId;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public int getTransactionTime() {
		return transactionTime;
	}

	public int gettShareQuantity() {
		return tShareQuantity;
	}

	public float gettSharePrice() {
		return tSharePrice;
	}

	public float gettCharge() {
		return tCharge;
	}

	public float gettFinalAmount() {
		return tFinalAmount;
	}

	public String gettType() {
		return tType;
	}
}
