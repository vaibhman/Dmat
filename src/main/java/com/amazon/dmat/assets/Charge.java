package com.amazon.dmat.assets;

/*
 * transactionCharge		: transaction charge of 0.5%
 * securitiesTransferTax	: of 0.1% of the overall transaction
 * totalCharge				: Total charge on transaction
 */


public class Charge {
	@SuppressWarnings("unused")
	private float transactionCharge;
	@SuppressWarnings("unused")
	private float securitiesTransferTax;	
	@SuppressWarnings("unused")
	private float totalCharge;
	
	private static float transactionChargeRate = 0.5f;
	private static float securitiesTransferTaxRate = 0.1f;
	
	private static Charge chargeObject;
	
	public Charge getInstance() {
		return chargeObject;
	}

	public static void setTransactionChargeRate(float transactionChargeRate) {
		Charge.transactionChargeRate = transactionChargeRate;
	}

	public static void setSecuritiesTransferTaxRate(float securitiesTransferTaxRate) {
		Charge.securitiesTransferTaxRate = securitiesTransferTaxRate;
	}

	public static float getTransactionCharge(float amount) {
		float tCharge = Math.max(100f,amount*transactionChargeRate/100);
		return tCharge ;
	}

	public static float getSecuritiesTransferTax(float amount) {
		float stt = amount*securitiesTransferTaxRate/100;
		return stt ;
	}

	public static float getTotalCharge(float amount) {
		return getTransactionCharge(amount)
				+getSecuritiesTransferTax(amount);
	}
	
	
	
}
