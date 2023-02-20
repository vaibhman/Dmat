package com.amazon.dmat.managers;

import java.sql.SQLException;

import com.amazon.dmat.assets.User;
import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.customExceptions.UserException;
import com.amazon.dmat.operations.OperationFactory;
import com.amazon.dmat.queryHelper.QueryBuilder;


/**
 * The class UserManager is a child class of BaseManager.
 * It works as a middle layer between the dbTools package/Lower Layer and the Operations
 * package/Upper Layer.
 * It contains functions related to userAccounts table such as read, create a record, validate data
 * from table, operation specific functions which require db support, etc.
 * It is used by the Upper Layers/Operations classes.
 * It utilizes Lower Layer/dbTools package and helper classes via the parent - BaseManager which
 * converts system exceptions to ApplicationExceptions.
 **/

public class UserManager extends BaseManager{
	
	private static UserManager userManager;

	public static UserManager getInstance() {
		if (userManager == null) {
			userManager = new UserManager();
		}
		return userManager;
	}
	
	

	public void create(User user) throws ApplicationException {
		QueryBuilder queryBuilder = this.getInsertInstance()
				.onTable("userAccounts")
				.insertValue("accountNo", user.getAccountNo())
				.insertValue("userName", user.getUserName())
				.insertValue("accountBalance", user.getAccountBalance())
				.insertValue("password", user.getPassword());
		
		String sqlQuery = this.buildQuery(queryBuilder);

		this.executeQuery(sqlQuery);
	}

	public void update(int accountNo, String field, String newValue) throws ApplicationException {
		QueryBuilder queryBuilder = this.getUpdateInstance()
				.updateValue(field, newValue)
				.whereEq("accountNo", accountNo)
				.onTable("userAccounts");
		String sqlQuery = this.buildQuery(queryBuilder);

		this.executeQuery(sqlQuery);
	}
	
	public boolean isValidUser(int accountNo) throws ApplicationException {
		QueryBuilder queryBuilder = this.getSelectInstance()
				.selectColumns("accountNo")
				.onTable("userAccounts")
				.whereEq("accountNo", accountNo);

		String sqlQuery = this.buildQuery(queryBuilder);

		return this.hasResult(sqlQuery);
	}

	public boolean isValidUserPassword(int accountNo, String password) throws ApplicationException {
		QueryBuilder queryBuilder = this.getSelectInstance()
				.selectColumns("accountNo")
				.onTable("userAccounts")
				.whereEq("accountNo", accountNo)
				.whereEq("password", password);

		String sqlQuery = this.buildQuery(queryBuilder);


		return this.hasResult(sqlQuery);
	}
	
	public float getAccountBalance(int accountNo) throws ClassNotFoundException, SQLException, ApplicationException {	

		QueryBuilder queryBuilder = this.getSelectInstance()
	              .selectColumns("accountBalance")
	              .onTable("userAccounts")
	              .whereEq("accountNo", accountNo);
		
		String sqlQuery = this.buildQuery(queryBuilder);
		return this.getQueryNumberFloat(sqlQuery);
	}
	
	public boolean setAccountBalance(int accountNo, float newValue) throws ClassNotFoundException, SQLException, ApplicationException {	

	    QueryBuilder queryBuilder = this.getUpdateInstance()
	            .onTable("userAccounts")
	            .updateValue("accountBalance", newValue)
	            .whereEq("accountNo", accountNo);

	    String sqlQuery = this.buildQuery(queryBuilder);

	    this.executeQuery(sqlQuery);
	    
		return true;
	}
	
	public boolean addMoney(int accountNo, float accountBalance) throws ClassNotFoundException, SQLException, ApplicationException, UserException {
		System.out.println("Enter Amount: ");
		float amount = this.getAmountInput(); 
		
		System.out.println("Enter upi id: ");
		@SuppressWarnings("unused")
		String upiId = OperationFactory.getScannerInstance().next(); 
		
		System.out.println("Enter password: ");
		@SuppressWarnings("unused")
		String pass = OperationFactory.getScannerInstance().next(); 
		
		float newValue = accountBalance+amount;

		return UserManager
		.getInstance()
		.setAccountBalance(accountNo, newValue);
	}
	
	public boolean withdrawMoney(int accountNo, float accountBalance) throws ClassNotFoundException, SQLException, ApplicationException, UserException {
		System.out.println("Enter Amount to Withdraw: ");
		float amount = this.getAmountInput(); 
		if(amount>accountBalance) {
			System.out.println("\nCan't withdraw more than Wallet balance\n");
			return false;
		}
		System.out.println("Enter Bank Account No. : ");
		@SuppressWarnings("unused")
		String bankNo = OperationFactory.getScannerInstance().next(); 

		float newValue = accountBalance-amount;

		return UserManager
		.getInstance()
		.setAccountBalance(accountNo, newValue);
	}
	
	public boolean displayAcDetails(int accountNo) throws ApplicationException {
		String[] columns= {"accountNo","userName","accountBalance", "password"};
		QueryBuilder queryBuilder = this.getSelectInstance()
	              .selectColumns(columns)
	              .onTable("userAccounts")
	              .whereEq("accountNo", accountNo);
		
		String sqlQuery = this.buildQuery(queryBuilder);
		
		this.executeQuery(sqlQuery, columns);
		
		return true;

	}
	

}
