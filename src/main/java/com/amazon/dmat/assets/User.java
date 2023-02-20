package com.amazon.dmat.assets;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * accountNo 		: Account number (Unique Id);
 * userName 		: User name; 
 * accountBalance	: Money in the account 
 * userShares		: List of shares
 * shareCounts		: hashmap<ShareName, number of shares baught>
*/

public class User {
	private int accountNo;
	private String userName;
	private float accountBalance;
	private String password;
	private ArrayList<Share> userShares;
	private HashMap<String, Integer> shareCounts;
	
	public User(int accountNo, String userName, float accountBalance, String password) {
		super();
		this.accountNo = accountNo;
		this.userName = userName;
		this.accountBalance = accountBalance;
		this.password = password;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public String getUserName() {
		return userName;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public ArrayList<Share> getUserShares() {
		return userShares;
	}

	public HashMap<String, Integer> getShareCounts() {
		return shareCounts;
	}

	public String getPassword() {
		return password;
	}
	
	
	
	
	
	
}
