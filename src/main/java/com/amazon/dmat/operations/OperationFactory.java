package com.amazon.dmat.operations;

import java.util.Scanner;

/**
 * The class OperationFactory is a Simple Factory implementation which returns new instances for all
 * classes and controls single-point instance creation of the Upper Layer/Operations package.
 * OperationFactory is utilized in the Upper-Layer/Operations package for getting on-the-fly instances
 * of classes under Operations folder which can be used in a single statement.
 **/

public class OperationFactory {

	public static Scanner getScannerInstance() {
		return new Scanner(System.in);
	}

	public static UserOperation getUserOperationInstance() {
		return new UserOperation();
	}
	
	public static UserLoginOperation getUserLoginInstance() {
		return new UserLoginOperation();
	}

}
