package com.amazon.dmat.assets;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.managers.IdManager;

public class AssetFactory {
	private static AssetFactory assetFactory;

	public static AssetFactory getInstance() {
		if (assetFactory == null) {
			assetFactory = new AssetFactory();
		}
		return assetFactory;
	}

	public User getUserInstance(String userName, float accountBalance, String password) throws ApplicationException {
		int accountNo =	IdManager.getInstance().getNewId("userAccounts");
		return new User(accountNo, userName, accountBalance, password);
	}
	
	public Share getShareInstance(int shareId, String shareName, float sharePrice) {
		return new Share(shareId, shareName, sharePrice);
	}
	
	public Transaction getTransactionInstance(String tType, int shareId, String shareName, float tSharePrice,
												int tShareQuantity, float tCharge, float tFinalAmount, 
												int accountNo) throws ApplicationException {
		
		int transactionId =	IdManager.getInstance().getNewId("transactions");	
		
		String transactionTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		return new Transaction(transactionId, tType, shareId, shareName, tSharePrice,
				 			tShareQuantity, tCharge, tFinalAmount, transactionTime, accountNo);
	}
	
	public CurrentHolding getCurrentHoldingInstance(int accountNo, int shareId, String shareName, int shareQuantity, float shareBuyPrice) {
		return new CurrentHolding(accountNo, shareId, shareName,shareQuantity,shareBuyPrice);
	}
}
