package com.asherdiaz;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	private Map<String, Slot> slots;
	Scanner scan = new Scanner(System.in);
	BankAccount account = new BankAccount();
	Inventory inventory = new Inventory();
	double balance;
	Slot product;
	String divider = "================================================";
	String choice = ">> ";
	String space = "";
	boolean productIsSelected = false;

	public void runMainMenu() throws IOException {
		boolean isValidInput = true;
		ArrayList<Product> inventoryList = inventory.getInventoryItems();

		while (isValidInput) {
			System.out.println(divider);
			System.out.println("		VENDO-MATIC 8000");
			System.out.println(divider);
			System.out.println(space);
			System.out.println("--MAIN MENU--");
			System.out.println("1) Display Vending Machine Items");
			System.out.println("2) Purchase");
			System.out.print(choice);
			String userMainMenuInput = scan.nextLine();
			if (userMainMenuInput.equals("1")) {
				System.out.println(space);
				System.out.println("--VENDING MACHINE ITEMS--");
				System.out.println(space);
				System.out.printf("%-10s %-20s %15s %n", "SLOT", "PRODUCT", "PRICE");
				System.out.printf("%-10s %-20s %15s %n", "----", "-------", "-----");
				for (Product product : inventoryList) {
					String slot = product.getSlotNumber();
					String name = product.getProductName();
					Double price = product.getPrice();
					DecimalFormat df = new DecimalFormat("0.00");
					System.out.printf("%-10s %-20s %15s %n", slot, name, "$" + df.format(price));
				}
				System.out.println(space);
			} else if (userMainMenuInput.equals("2")) {
				runPurchaseMenu();
			} else {
				System.out.println(space);
				System.out.println("ERROR: Please Select A Valid Menu Option");
				System.out.println(space);
				continue;

			}
		}
		scan.close();

	}

	private void runPurchaseMenu() throws IOException {
		boolean isValidInput = true;
		String space = "";
		String choice = ">> ";
		Scanner scan = new Scanner(System.in);

		while (isValidInput) {
			System.out.println(space);
			System.out.println("--PURCHASE MENU--");
			System.out.println("1) Feed Money");
			System.out.println("2) Select Product");
			System.out.println("3) Finish Transaction");
			System.out.println("4) Return to Main Menu");
			System.out.print(choice);
			String userPurchaseInput = scan.nextLine();
			if (userPurchaseInput.equals("1")) {
				feedMoney();
			} else if (userPurchaseInput.equals("2")) {
				selectProduct();
			} else if (userPurchaseInput.equals("3")) {
				if (productIsSelected == false) {
					System.out.println(space);
					System.out.println("ERROR: Please select a product for purchase.");
					System.out.println(space);
					runPurchaseMenu();
				}
				else if((balance <= 0) || (balance < product.getProduct().getPrice())) {
					System.out.println(space);
					System.out.println("ERROR: Insufficient Funds. Please feed more money.");
					System.out.println(space);
					runPurchaseMenu();
				}
				else {
					finishTransaction();
				}
			} else if (userPurchaseInput.equals("4")) {
				runMainMenu();
			} else {
				System.out.println(space);
				System.out.println("ERROR: Please Select A Valid Menu Option");
				System.out.println(space);
				continue;
			}
			break;
		}
		scan.close();
	}

	private void feedMoney() throws IOException {

		System.out.println(space);
		System.out.println("Enter Deposit Amount >> ");
		System.out.println(space);

		balance = 0;
		account.amountDeposited = Double.parseDouble(scan.nextLine());
		account.depositMoney();
		balance = account.accountBalance;
		account.setAccountBalance(balance);
		DecimalFormat df = new DecimalFormat("#.00");

		System.out.println("Account Balance : $" + df.format(balance));
		// scan.close();
		runPurchaseMenu();
	}

	private void selectProduct() throws IOException {

		slots = inventory.getInventory();

		System.out.println("--Purchase Product--");
		System.out.println("Enter Slot # >> ");

		String key = scan.nextLine().toUpperCase();
		if (!slots.containsKey(key)) {
			System.out.println("ERROR: Invalid slot number. Please enter valid slot number.");
			selectProduct();
		}

		product = slots.get(key);
		productIsSelected = true;

		String productName = product.getProduct().getProductName();
		Double price = product.getProduct().getPrice();
		DecimalFormat df = new DecimalFormat("#.00");

		System.out.println("You are purchasing " + productName + " for $" + df.format(price));
		System.out.println("Select 3 to Finish Transaction");
		runPurchaseMenu();

	}

	private void finishTransaction() throws IOException {
		double accountBalance = account.getAccountBalance();
		double purchaseAmount = product.getProduct().getPrice();
		account.setPurchaseAmount(purchaseAmount);
		if (accountBalance < purchaseAmount) {
			System.out.println(
					"There is not enough money in your account to make this purchase. Please insert more money.");
			runPurchaseMenu();
		} else {
			double updatedBalance = account.makePurchase(accountBalance, purchaseAmount);
			String productName = product.getProduct().getProductName();
			DecimalFormat df = new DecimalFormat("#.00");
			System.out.println(space);
			System.out.println("You have purchased : " + productName);
			System.out.println("Account Balance : $" + df.format(updatedBalance));
			System.out.println(space);
			account.setAccountBalance(updatedBalance);
			int quantity = product.getQuantity();
			int updatedQuantity = quantity - 1;
			product.setQuantity(updatedQuantity);
			runPurchaseMenu();
		}

	}

}
