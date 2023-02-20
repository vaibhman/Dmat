package com.amazon.dmat.managers;

import com.amazon.dmat.assets.Transaction;
import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.queryHelper.QueryBuilder;

/**
 * The class TransactionManager is a child class of BaseManager.
 * It works as a middle layer between the dbTools package/Lower Layer and the Operations
 * package/Upper Layer.
 * It contains functions related to transactions table such as read, create a record, validate data
 * from table, operation specific functions which require db support, etc.
 * It is used by the Upper Layers/Operations classes.
 * It utilizes Lower Layer/dbTools package and helper classes via the parent - BaseManager which
 * converts system exceptions to ApplicationExceptions.
 **/

public class TransactionManager extends BaseManager {

	  private static TransactionManager transactionManager ;

	  public static TransactionManager getInstance() {
	    if (transactionManager == null) {
	    	transactionManager = new TransactionManager();
	    }
	    return transactionManager ;
	  }

	  public void create(Transaction transaction) throws ApplicationException {
	    QueryBuilder queryBuilder = this.getInsertInstance()
	            .onTable("transactions")
	            .insertValue("transactionId", transaction.getTransactionId())
	            .insertValue("tType",transaction.gettType())
	            .insertValue("shareId", transaction.getShareId())
	            .insertValue("shareName", transaction.getShareName())
	            .insertValue("tSharePrice", transaction.gettSharePrice())
	            .insertValue("tShareQuantity", transaction.gettShareQuantity())
	            .insertValue("tCharge",transaction.gettCharge())
	            .insertValue("tFinalAmount",transaction.gettFinalAmount())
	            .insertValue("transactionTime", transaction.getTransactionTime())
	            .insertValue("accountNo", transaction.getAccountNo());

	    String sqlQuery = this.buildQuery(queryBuilder);

	    this.executeQuery(sqlQuery);
	  }

	  public void update(int transactionId, String field, int newValue) throws ApplicationException {
	    QueryBuilder queryBuilder = this.getUpdateInstance()
	            .onTable("transactions")
	            .updateValue(field, newValue)
	            .whereEq("transactionId", transactionId);

	    String sqlQuery = this.buildQuery(queryBuilder);

	    this.executeQuery(sqlQuery);
	  }
	  
	  public boolean viewUserTransactions(int accountNo) throws ApplicationException {
		  String[] columns= {"transactionId", "tType", "shareName","tSharePrice","tShareQuantity","tCharge", "tFinalAmount", "transactionTime"};

		  QueryBuilder queryBuilder = this.getSelectInstance()
				  .selectColumns(columns)
				  .onTable("currentHoldings")
				  .whereEq("accountNo", accountNo);

		  String sqlQuery = this.buildQuery(queryBuilder);

		  if (!this.hasResult(sqlQuery)) {
			  System.out.println("You Do Not Have Any transactions in Your Account");
			  return false;
		  }

		  String[] headers = {"TRANSACTION ID", "BUY/SELL","SHARE NAME","PRICE","QUANTITY", "CHARGE/TAX","TIME"};
		  this.executeQuery(sqlQuery, headers);

		  return true;
	  }
}
