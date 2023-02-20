package com.amazon.dmat.managers;

import com.amazon.dmat.assets.Share;
import com.amazon.dmat.assets.User;
import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.queryHelper.QueryBuilder;

/**
 * The class ShareManager is a child class of BaseManager.
 * It works as a middle layer between the dbTools package/Lower Layer and the Operations
 * package/Upper Layer.
 * It contains functions related to shares table such as read, create a record, validate data
 * from table, operation specific functions which require db support, etc.
 * It is used by the Upper Layers/Operations classes.
 * It utilizes Lower Layer/dbTools package and helper classes via the parent - BaseManager which
 * converts system exceptions to ApplicationExceptions.
 **/

public class ShareManager extends BaseManager{
	
	private static ShareManager shareManager;

	public static ShareManager getInstance() {
		if (shareManager == null) {
			shareManager = new ShareManager();
		}
		return shareManager;
	}
	
	
	public void create(Share share) throws ApplicationException {
		QueryBuilder queryBuilder = this.getInsertInstance()
				.onTable("shares")
				.insertValue("shareId", share.getShareId())
				.insertValue("shareName", share.getShareName())
				.insertValue("sharePrice", share.getSharePrice());
		
		String sqlQuery = this.buildQuery(queryBuilder);

		this.executeQuery(sqlQuery);
	}

	public void update(int shareId, String field, String newValue) throws ApplicationException {
		QueryBuilder queryBuilder = this.getUpdateInstance()
				.updateValue(field, newValue)
				.whereEq("shareId", shareId)
				.onTable("shares");
		String sqlQuery = this.buildQuery(queryBuilder);

		this.executeQuery(sqlQuery);
	}
	
	public boolean viewAllShares() throws ApplicationException {
		String[] columns = {"shareId", "shareName", "sharePrice"};

		QueryBuilder queryBuilder = this.getSelectInstance()
				.selectColumns(columns)
				.onTable("shares");

		String sqlQuery = this.buildQuery(queryBuilder);

		if (!this.hasResult(sqlQuery)) {
			System.out.println("No Shares Found");
			return false;
		}

		String[] headers = {"SHARE ID", "SHARE NAME", "PRICE"};
		this.executeQuery(sqlQuery, headers);
		return true;
	}

	public boolean isShareExist(int shareId) throws ApplicationException {
	    QueryBuilder queryBuilder = this.getSelectInstance()
	            .selectColumns("shareId")
	            .onTable("shares")
	            .whereEq("shareId", shareId);

	    String sqlQuery = this.buildQuery(queryBuilder);

	    return this.hasResult(sqlQuery);
	}

	public String getShareName(int shareId) throws ApplicationException {
		QueryBuilder queryBuilder = this.getSelectInstance()
	              .selectColumns("shareName")
	              .onTable("shares")
	              .whereEq("shareId", shareId);
		
		String sqlQuery = this.buildQuery(queryBuilder);
		System.out.println(sqlQuery);
		return this.getQueryString(sqlQuery);
	}
}
