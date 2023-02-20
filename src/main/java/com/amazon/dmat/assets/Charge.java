package com.amazon.dmat.assets;

/*
 * transactionCharge		: transaction charge of 0.5%
 * securitiesTransferTax	: of 0.1% of the overall transaction
 * totalCharge				: Total charge on transaction
 */


public class Charge {
	private float transactionCharge;
	private float securitiesTransferTax;	
	private float totalCharge;
	
	private static float transactionChargeRate = 0.5f;
	private static float securitiesTransferTaxRate = 0.1f;
	
	private static Charge chargeObject;
	
	public Charge getInstance() {
		return chargeObject;
	}

	public static float getTransactionChargeRate() {
		return transactionChargeRate;
	}

	public static void setTransactionChargeRate(float transactionChargeRate) {
		Charge.transactionChargeRate = transactionChargeRate;
	}

	public static float getSecuritiesTransferTaxRate() {
		return securitiesTransferTaxRate;
	}

	public static void setSecuritiesTransferTaxRate(float securitiesTransferTaxRate) {
		Charge.securitiesTransferTaxRate = securitiesTransferTaxRate;
	}

	public float getTransactionCharge() {
		return transactionCharge;
	}

	public float getSecuritiesTransferTax() {
		return securitiesTransferTax;
	}

	public float getTotalCharge() {
		return totalCharge;
	}
	
	
	
}
