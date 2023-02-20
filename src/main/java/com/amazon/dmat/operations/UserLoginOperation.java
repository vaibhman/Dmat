package com.amazon.dmat.operations;

import java.sql.SQLException;

import com.amazon.dmat.assets.AssetFactory;
import com.amazon.dmat.assets.User;
import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.customExceptions.UserException;
import com.amazon.dmat.managers.UserManager;

public class UserLoginOperation extends BaseOperation{
	
	private final static int maxLoginTries = 5;
	private static int loginTries = 0;
	
	public void initiate()  {
		/*a. Create a Demat account - Create a D-MAT account with needed details
			(Mentioned Below).
			b. Login - It should ask for the account number in order to login (The account
			number should be an integer value).*/

		boolean exitCode = false;

		while (!exitCode) {

			System.out.println("\nSelect an option :");
			System.out.println("\n 1. Create a Demat account "
					+ "\n 2. Login "
					+ "\n 0. Exit ");

			String choice = OperationFactory.getScannerInstance().next();

			switch (choice) {
			case "1":
				try {
					createAccount();
				} catch (ApplicationException | UserException | ClassNotFoundException | SQLException e) {
					System.out.print("Something Went Wrong");
					e.printStackTrace();
				}
				break;

			case "2":
				try {
					setLoginDetails();
				} catch (ApplicationException | UserException | ClassNotFoundException | SQLException e) {
					System.out.print("Something Went Wrong");
					e.printStackTrace();
				}
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

	private void createAccount() throws ApplicationException, UserException, ClassNotFoundException, SQLException {
		System.out.println("\nCreate your new User account");
		System.out.println("\nPlease Enter the below details as prompted and Press Enter to confirm entry." +
				"\nPress Enter Twice to return to Previous Menu. ");

		//System.out.println("\n Account Number [9 Digit Number]: \n");


		System.out.println("\n Name : \n");
		String name = this.getName();;

		System.out.println("\n Password : \n" +
				"[Should be of at least 8 characters, contain only letters and digits and " +
				"must contain at least 2 digits]");
		String password = this.getPassword();

		System.out.println("\n Confirm Password : \n" +
				"[Should be the same value as entered before]");
		String confirmedPassword = this.getConfirmedPassword(password);

		float accountBalance = 0;

		User user = AssetFactory.getInstance().getUserInstance(name, accountBalance, confirmedPassword);

		UserManager.getInstance().create(user);

		System.out.println("Your Account with Account Number: " + user.getAccountNo() +
				" has been created ! \n");
		System.out.println("Please Login with your Account Number and Password below : \n");

		setLoginDetails();

	}
	
	private boolean setLoginDetails() throws ApplicationException, UserException, ClassNotFoundException, SQLException {
		loginTries += 1;
		System.out.println("------------------------------------------");
		System.out.println("\nUser Login \n");
		System.out.println("Enter Account Number : \n");
		int accountNo = this.getAccountNo();

		System.out.println("Enter Password : \n");
		String password = this.getPassword();

		if (loginTries > maxLoginTries) {
			System.out.println("Maximum Login Tries Exceeded! \n Returning to Home.");

			loginTries = 0;
			return false;
		}
		login(accountNo, password);
		
		return true;
	}
	
	private void login(int accountNo, String password) throws ApplicationException, UserException, ClassNotFoundException, SQLException {
		
		if (UserManager.getInstance().isValidUserPassword(accountNo, password)) {
			System.out.println("Account Login Successful!");
			OperationFactory.getUserOperationInstance().showUserMenu(accountNo); 
		} else {
			System.out.println("\nUnable to load account with entered credentials. " +
					"\nPlease make sure that the entered credentials are correct \n");
			setLoginDetails();
		}
	}

}
