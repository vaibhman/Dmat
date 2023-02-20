package com.amazon.dmat.managers;

import com.amazon.dmat.assets.CurrentHolding;
import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.queryHelper.QueryBuilder;

public class CurrentHoldingManager extends BaseManager{
	
	private static CurrentHoldingManager currentHoldingManager;

	public static CurrentHoldingManager getInstance() {
		if (currentHoldingManager == null) {
			currentHoldingManager = new CurrentHoldingManager();
		}
		return currentHoldingManager;
	}
	
	
	public void create(CurrentHolding currentHolding) throws ApplicationException {
		QueryBuilder queryBuilder = this.getInsertInstance()
				.onTable("currentHoldings")
				.insertValue("accountNo", currentHolding.getAccountNo())
				.insertValue("shareId", currentHolding.getShareId())
				.insertValue("shareName", currentHolding.getShareName())
				.insertValue("shareQuantity", currentHolding.getShareQuantity())
				.insertValue("shareBuyPrice", currentHolding.getShareBuyPrice());
		
		String sqlQuery = this.buildQuery(queryBuilder);

		this.executeQuery(sqlQuery);
	}

}
