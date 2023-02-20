package com.amazon.dmat.operations;

public class UserOperation extends BaseOperation{
	void showUserMenu(int accountNo) {
		boolean exitCode = false;

		while (!exitCode) {

			System.out.println("\nEnter your User Type :");
			System.out.println("\n 1. "
					+ "\n 2. "
					+ "\n 0. Exit ");

			String choice = OperationFactory.getScannerInstance().next();

			switch (choice) {
			case "1":
				break;

			case "2":
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

}
