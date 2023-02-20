package com.amazon.dmat.operations;

import java.sql.SQLException;

import com.amazon.dmat.assets.AssetFactory;
import com.amazon.dmat.assets.Charge;
import com.amazon.dmat.assets.CurrentHolding;
import com.amazon.dmat.assets.Transaction;
import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.customExceptions.UserException;
import com.amazon.dmat.managers.CurrentHoldingManager;
import com.amazon.dmat.managers.ShareManager;
import com.amazon.dmat.managers.TransactionManager;
import com.amazon.dmat.managers.UserManager;

public class UserOperation extends BaseOperation{
	
	void showUserMenu(int accountNo) throws ClassNotFoundException, SQLException, ApplicationException, UserException {
		
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
					+ "\n 4. Buy Shares"
					+ "\n 5. Sell Shares"
					+ "\n 6. View transaction report"
					+ "\n 0. Exit this Menu ");

			String choice = OperationFactory.getScannerInstance().next();

			switch (choice) {
			case "1":
				displayAcDetails(accountNo);
				break;

			case "2":
				depositMoney(accountNo, accountBalance);

				break;
				
			case "3":
				withdrawMoney(accountNo, accountBalance);
				break;
				
			case "4":
				buyShare(accountNo, accountBalance);
				break;
				
			case "5":
				sellShare(accountNo, accountBalance);
				break;
				
			case "6":
				viewTransactions(accountNo);
				break;

			case "0":
				exitCode = true;
				break;

			default:
				System.out.println("Please Enter Valid Option");
			}
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("---Thank You For Using our Dmat Application---\n");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}


	private boolean displayAcDetails(int accountNo) throws ApplicationException {
		System.out.println("Your Account Details are: ");
		UserManager.getInstance().displayAcDetails(accountNo);
		return true;
	}

	private void depositMoney(int accountNo, float accountBalance) {
		try {
			if(UserManager.getInstance().addMoney(accountNo, accountBalance)) {
				System.out.println("\nMoney Added Successfully");
				float newAccountBalance= UserManager.getInstance().getAccountBalance(accountNo);
				System.out.println("Updated Account Balance is: "+newAccountBalance);
			}
		} catch (ClassNotFoundException | SQLException | ApplicationException | UserException e) {
			System.out.println("Something Went Wrong...");
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------");
	}

	private void withdrawMoney(int accountNo, float accountBalance) {
		try {
			if(UserManager.getInstance().withdrawMoney(accountNo, accountBalance)) {
				System.out.println("\nMoney Withdrawed Successfully \n"
						+ "The money will be credited to your account within 3-5 business days\n");
				float newAccountBalance= UserManager.getInstance().getAccountBalance(accountNo);
				System.out.println("Updated Account Balance is: "+newAccountBalance);
			}
		} catch (ClassNotFoundException | SQLException | ApplicationException | UserException e) {
			System.out.println("Something Went Wrong...");
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------");
	}

	private boolean buyShare(int accountNo, float accountBalance) throws ApplicationException, UserException, ClassNotFoundException, SQLException {
		System.out.println("For Your Reference...");
		System.out.println("Account Balance: "+accountBalance);
		System.out.println("------------List of Shares------------");
		ShareManager.getInstance().viewAllShares();
		
		String tType="Buy";
		
		System.out.println("\nEnter Share ID: ");
		int shareId = this.getShareIdInput();
		
		String shareName = ShareManager.getInstance().getShareName(shareId);
		float tSharePrice = ShareManager.getInstance().getSharePrice(shareId);
		
		System.out.println("\nEnter Share Quantity to Buy: ");
		int tShareQuantity = this.getShareQuantityInput();

		float amount = tSharePrice*tShareQuantity;

		float tCharge = Charge.getTotalCharge(amount);

		float tFinalAmount = amount + tCharge;

		if(tFinalAmount>accountBalance) {
			System.out.println("Not Enough Balance To Complete The Transaction");
			System.out.println("Please Use Deposit Money to Add Funds to Your Account");
			return false;
		}
		
		Transaction newTransaction = AssetFactory
									.getInstance()
									.getTransactionInstance(tType, shareId, shareName, 
											tSharePrice, tShareQuantity, tCharge, 
											tFinalAmount, accountNo);
		
		UserManager.getInstance().setAccountBalance(accountNo, accountBalance-tFinalAmount);
		
		TransactionManager.getInstance().create(newTransaction);
		
		if(CurrentHoldingManager
				.getInstance()
				.isShareInUserAccount(accountNo,shareId)) {
			
			int availableQuantity = CurrentHoldingManager.getInstance().availableQuantity(accountNo, shareId);
			
			CurrentHoldingManager
				.getInstance()
				.update(accountNo, shareId, "shareQuantity", availableQuantity+tShareQuantity);
		
		}else {
			
			CurrentHolding newHolding = AssetFactory
					.getInstance()
					.getCurrentHoldingInstance(accountNo, shareId, shareName, tShareQuantity, tSharePrice);
			
			CurrentHoldingManager
				.getInstance()
				.create(newHolding);

		}
		

		
		System.out.println("\nTransaction Successfull!!!");
		System.out.println(tShareQuantity+" share of "+shareName+" Added to Your Account");
		float newBalance= UserManager.getInstance().getAccountBalance(accountNo);
		System.out.println("Your Updated Account Balance is: "+newBalance);
		
		System.out.println("--------------------------------------------------------");
		return true;
	}


	private boolean sellShare(int accountNo, float accountBalance) throws ApplicationException, UserException, ClassNotFoundException, SQLException {
		System.out.println("For Your Reference...");
		
		if(!CurrentHoldingManager.getInstance().viewUserShares(accountNo)) {
			return false;
		}
		
		String tType="Sell";
		
		System.out.println("\nEnter Share ID: ");
		int shareId = this.getShareIdInput();
		
		String shareName = ShareManager.getInstance().getShareName(shareId);
		float tSharePrice = ShareManager.getInstance().getSharePrice(shareId);
		int availableQuantity = CurrentHoldingManager.getInstance().availableQuantity(accountNo, shareId);
		
		System.out.println("\nEnter Share Quantity to Sell: ");
		
		int tShareQuantity = this.getShareQuantityInput();

		if(availableQuantity<1 || availableQuantity<tShareQuantity) {
			System.out.println("Not Enough Shares.....");
			return false;
		}
		
		float amount = tSharePrice*tShareQuantity;

		float tCharge = Charge.getTotalCharge(amount);

		float tFinalAmount = amount - tCharge;

		Transaction newTransaction = AssetFactory
									.getInstance()
									.getTransactionInstance(tType, shareId, shareName, 
											tSharePrice, tShareQuantity, tCharge, 
											tFinalAmount, accountNo);

		UserManager.getInstance().setAccountBalance(accountNo, accountBalance+tFinalAmount);

		TransactionManager.getInstance().create(newTransaction);
		
		CurrentHoldingManager
				.getInstance()
				.update(accountNo, shareId, "shareQuantity", availableQuantity-tShareQuantity);
		
		int newAvailableQuantity = CurrentHoldingManager
											.getInstance()
											.availableQuantity(accountNo, shareId);
		
		if(newAvailableQuantity<1){
			CurrentHoldingManager.getInstance().delete(accountNo, shareId);
		}		
		
		System.out.println("\nTransaction Successfull !!!");
		System.out.println(tShareQuantity+" share of "+shareName+" Sold!");
		float newBalance= UserManager.getInstance().getAccountBalance(accountNo);
		System.out.println("Your Updated Account Balance is: "+newBalance);
		
		System.out.println("--------------------------------------------------------");
		return true;
	}

	
	private void viewTransactions(int accountNo) throws ApplicationException {
		TransactionManager.getInstance().viewUserTransactions(accountNo);
	}
}
