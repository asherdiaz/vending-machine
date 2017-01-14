package com.asherdiaz;

public class Slot {
	
	private String slotNumber;
	private Product product;
	private int quantity;
	
	public String getSlotNumber() {
		return slotNumber;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Slot(String slotNumber, Product product, int quantity) {
		slotNumber = this.slotNumber;
		product = this.product;
		quantity = this.quantity;
	}

}
