package com.asherdiaz;

public class Product {

	private String slotNumber;
	private String productName;
	private double price;
	
	public Product(String slot, String productName, double price) {
		productName = this.productName;
		price = this.price;
		slot = this.slotNumber;
	}
	
	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slot) {
		this.slotNumber = slot;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
