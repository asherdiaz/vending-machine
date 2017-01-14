package com.asherdiaz;

public class BankAccount {
	
	double accountBalance;
	double amountDeposited;
	double purchaseAmount;
	
	public double depositMoney() {
		accountBalance = accountBalance  + amountDeposited;
		return accountBalance;
	}
	
	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getAmountDeposited() {
		return amountDeposited;
	}

	public void setAmountDeposited(double amountDeposited) {
		this.amountDeposited = amountDeposited;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public double makePurchase(double accountBalance, double purchaseAmount) {
		accountBalance = accountBalance - purchaseAmount;
		return accountBalance;
	}
	

}
