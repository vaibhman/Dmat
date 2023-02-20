package com.amazon.dmat.operations;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.amazon.dmat.customExceptions.ApplicationException;
import com.amazon.dmat.customExceptions.UserException;
import com.amazon.dmat.dbtools.Validator;
import com.amazon.dmat.managers.UserManager;

public class BaseOperation {

	private final static int MAX_PASSWORD_CONFIRM_TRIES = 3;
	private final static int MAX_PASSWORD_TRIES = 3;
	private final static int MAX_EXISTING_PASSWORD_TRIES = 3;
	
	
	//User Parameters
	protected int getAccountNo() throws UserException {
		Scanner sc = OperationFactory.getScannerInstance();

		int userId;

		try {
			userId = sc.nextInt();
		} catch (InputMismatchException e) {
			throw new UserException("\n Please enter correct Account Number. " +
					"\n It is a 9 digit number\n");
		}

		if (!Validator.isPositive(userId)) {
			throw new UserException("\n Account Number cannot be a negative number.");
		}

		if (!Validator.isValidUserIdLength(userId)) {
			throw new UserException("The entered value is not a valid Account Number" +
					"\nIt is a 9 digit number\n");
		}
		return userId;
	}

	protected String getName() throws UserException {
		Scanner sc = OperationFactory.getScannerInstance();

		String name;

		try {
			name = sc.nextLine();
		} catch (InputMismatchException e) {
			throw new UserException("Entered Name is Invalid");
		}

		if (!Validator.isValidNameLength(name)) {
			throw new UserException("Name should be below 50 characters ");
		}

		if (!Validator.isAlphabeticWithSpaceAndDots(name)) {
			throw new UserException(" Name can only contain alphabets, spaces and dots in " +
					"appropriate manner\n");
		}
		return name;
	}

	protected String getPassword() throws UserException {
		Scanner sc = OperationFactory.getScannerInstance();

		String password;
		int passwordTries = 1;

		try {
			password = sc.next();
		} catch (InputMismatchException e) {
			throw new UserException("Entered Password is Invalid ");
		}

		while (passwordTries < MAX_PASSWORD_TRIES) {
			if (!Validator.isValidPassword(password)) {
				++passwordTries;
				System.out.println("Please Enter a Valid password [Only 3 tries Allowed]: \n" +
						" 1. A password must have at least eight characters.\n" +
						" 2. A password consists of only letters and digits.\n" +
						" 3. A password must contain at least two digits \n");
				try {
					password = sc.next();
				} catch (InputMismatchException e) {
					throw new UserException("Entered Password is Invalid ");
				}
			} else {
				return password;
			}
		}
		if (!Validator.isValidPassword(password)) {
			throw new UserException("Password tries Exhausted");
		}
		return password;
	}

	// Parameters
	protected String getConfirmedPassword(String password) throws UserException {
		Scanner sc = OperationFactory.getScannerInstance();

		String confirmedPassword;
		int passwordConfirmTries = 1;

		try {
			confirmedPassword = sc.next();
		} catch (InputMismatchException e) {
			throw new UserException("Entered Password is Invalid ");
		}

		while (passwordConfirmTries <= MAX_PASSWORD_CONFIRM_TRIES) {
			if (!Validator.arePasswordsMatching(password, confirmedPassword)) {
				++passwordConfirmTries;
				System.out.println("Please Enter a password which matches previous password value " +
						"[Only 3 tries Allowed]: \n");
				try {
					confirmedPassword = sc.next();
				} catch (InputMismatchException e) {
					throw new UserException("Entered Password is Invalid ");
				}
			} else {
				return confirmedPassword;
			}
		}
		if (!Validator.arePasswordsMatching(password, confirmedPassword)) {
			throw new UserException("Both Passwords do not match. Password tries Exhausted");
		}
		return confirmedPassword;
	}

	protected String getExistingPassword(int userId) throws UserException, ApplicationException {
		Scanner sc = OperationFactory.getScannerInstance();

		String existingPassword;
		int passwordEntryCount = 1;

		try {
			existingPassword = sc.next();
		} catch (InputMismatchException e) {
			throw new UserException("Entered Password is Invalid ");
		}

		while (passwordEntryCount <= MAX_EXISTING_PASSWORD_TRIES) {
			if (!UserManager.getInstance().isValidUserPassword(userId, existingPassword)) {
				++passwordEntryCount;
				System.out.println("Please enter value which matches current password " +
						"[Only 3 tries Allowed]: \n");
				try {
					existingPassword = sc.next();
				} catch (InputMismatchException e) {
					throw new UserException("Entered Password is invalid.");
				}
			} else {
				return existingPassword;
			}
		}
		if (!UserManager.getInstance().isValidUserPassword(userId, existingPassword)) {
			throw new UserException("Entered Password does not match existing value." +
					"Password tries Exhausted");
		}
		return existingPassword;
	}
	
	protected boolean arePasswordsMatching(String oldPassword, String newPassword) {
		return Validator.arePasswordsMatching(oldPassword,newPassword);
	}

}
