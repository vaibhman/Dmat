package com.amazon.dmat.operations;

import java.sql.SQLException;

import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.customExceptions.UserException;
import com.amazon.dmat.managers.UserManager;

public class UserOperation extends BaseOperation{
	
	void showUserMenu(int accountNo) throws ClassNotFoundException, SQLException, ApplicationException {
		
		System.out.println("--------------------------------------");
		System.out.println("------------- Welcome User ------------");
		System.out.println("--------------------------------------");
		
		boolean exitCode = false;
		
		while (!exitCode) {
			float accountBalance= UserManager.getInstance().getAccountBalance(accountNo);

			System.out.println("\nSelect an Option :");
			System.out.println("\n 1. Display Demat account details "
					+ "\n 2. Deposit Money"
					+ "\n 3. Withdraw Money"
					+ "\n 4. Buy transaction"
					+ "\n 5. Sell transaction"
					+ "\n 6. View transaction report"
					+ "\n 0. Exit this Menu ");

			String choice = OperationFactory.getScannerInstance().next();

			switch (choice) {
			case "1":
				displayAcDetails(accountNo);
				break;

			case "2":
				
				try {
					UserManager.getInstance().addMoney(accountNo, accountBalance);
				} catch (ClassNotFoundException | SQLException | ApplicationException | UserException e) {
					System.out.println("Something Went Wrong...");
					e.printStackTrace();
				}
				
				break;
				
			case "3":
				try {
					UserManager.getInstance().withdrawMoney(accountNo, accountBalance);
				} catch (ClassNotFoundException | SQLException | ApplicationException | UserException e) {
					System.out.println("Something Went Wrong...");
					e.printStackTrace();
				}
				break;
				
			case "4":
				break;
				
			case "5":
				break;
				
			case "6":
				break;

			case "0":
				exitCode = true;
				break;

			default:
				System.out.println("Please Enter Valid Option");
			}
		}

		System.out.println("Thank You For Using our Dmat Application\n");
	}

	private void displayAcDetails(int accountNo) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
