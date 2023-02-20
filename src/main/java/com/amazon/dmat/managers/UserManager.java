package com.amazon.dmat.managers;

import com.amazon.dmat.assets.User;
import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.queryHelper.QueryBuilder;

public class UserManager extends BaseManager{
	private static UserManager userAcManager;

	public static UserManager getInstance() {
		if (userAcManager == null) {
			userAcManager = new UserManager();
		}
		return userAcManager;
	}
	
	

	public void create(User user) throws ApplicationException {
		QueryBuilder queryBuilder = this.getInsertInstance()
				.onTable("userAccounts")
				.insertValue("accountNo", user.getAccountNo())
				.insertValue("name", user.getUserName())
				.insertValue("email", user.getAccountBalance());
		
		String sqlQuery = this.buildQuery(queryBuilder);

		this.executeQuery(sqlQuery);
	}

	public void update(int accountNo, String field, String newValue) throws ApplicationException {
		QueryBuilder queryBuilder = this.getUpdateInstance()
				.updateValue(field, newValue)
				.whereEq("accountNo", accountNo)
				.onTable("user");
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
	
}
