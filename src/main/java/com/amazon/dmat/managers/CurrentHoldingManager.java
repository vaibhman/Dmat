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
	
	public void update(int accountNo, int shareId, String field, int newValue) throws ApplicationException {
		QueryBuilder queryBuilder = this.getUpdateInstance()
				.onTable("currentHoldings")
				.updateValue(field, newValue)
				.whereEq("accountNo", accountNo)
				.whereEq("shareId", shareId);

		String sqlQuery = this.buildQuery(queryBuilder);

		this.executeQuery(sqlQuery);
	}
	
	  public void delete(int accountNo, int shareId) throws ApplicationException {
		    QueryBuilder queryBuilder = this.getDeleteInstance()
		            .onTable("currentHoldings")
		            .whereEq("accountNo", accountNo)
		            .whereEq("shareId", shareId);

		    String sqlQuery = this.buildQuery(queryBuilder);

		    this.executeQuery(sqlQuery);
		  }
	
	public boolean viewUserShares(int accountNo) throws ApplicationException {
		String[] columns= {"shareId","shareName", "shareQuantity", "shareBuyPrice"};
		
		QueryBuilder queryBuilder = this.getSelectInstance()
				.selectColumns(columns)
				.onTable("currentHoldings")
				.whereEq("accountNo", accountNo);

		String sqlQuery = this.buildQuery(queryBuilder);
		
		if (!this.hasResult(sqlQuery)) {
			System.out.println("You Do Not Have Any Shares in Your Account");
			return false;
		}
		
		String[] headers = {"SHARE ID","SHARE NAME", "QUANTITY", "BUY PRICE"};
		this.executeQuery(sqlQuery, headers);
		
		return true;
	}

	public int availableQuantity(int accountNo, int shareId) throws ApplicationException {
		QueryBuilder queryBuilder = this.getSelectInstance()
				.selectColumns("shareQuantity")
				.onTable("currentHoldings")
				.whereEq("accountNo", accountNo)
				.whereEq("shareId",shareId);

		String sqlQuery = this.buildQuery(queryBuilder);
		
		return this.getQueryNumber(sqlQuery);
	}

	public boolean isShareInUserAccount(int accountNo, int shareId) throws ApplicationException {
		QueryBuilder queryBuilder = this.getSelectInstance()
				.selectColumns("shareQuantity")
				.onTable("currentHoldings")
				.whereEq("accountNo", accountNo)
				.whereEq("shareId", shareId);

		String sqlQuery = this.buildQuery(queryBuilder);
		
	    return this.hasResult(sqlQuery);
	}
}
