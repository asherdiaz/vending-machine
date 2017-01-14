package com.asherdiaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
	
public class Inventory {

	private Map<String, Slot> inventory;

	public ArrayList<Product> getInventoryItems() throws IOException {
		File inventoryFile = new File("inventoryList.txt");
		ArrayList<Product> inventoryItems = new ArrayList<>();
		try (FileReader fr = new FileReader(inventoryFile); BufferedReader br = new BufferedReader(fr);) {
			String line = null;

			while ((line = br.readLine()) != null) {
				String delimiter = "\\|";
				String[] items = line.split(delimiter);

				for (int i = 0; i < items.length; i += 3) {
					String slot = items[i];
					String name = items[i + 1];
					String priceString = items[i + 2];
					double price = Double.parseDouble(priceString);
					Product product = new Product(slot, name, price);
					product.setSlotNumber(slot);
					product.setPrice(price);
					product.setProductName(name);
					inventoryItems.add(product);
				}
			}
		}
		return inventoryItems;
	}

	public Map<String, Slot> stockInventory() throws IOException {
		Inventory inventoryList = new Inventory();
		ArrayList<Product> products = inventoryList.getInventoryItems();
		Map<String, Slot> inventory = new HashMap<>();
		
		for(Product product : products) {
			String slotNumber = product.getSlotNumber();
			int quantity = 5;
			Slot slot = new Slot(slotNumber, product, quantity);
			slot.setProduct(product);
			slot.setQuantity(quantity);
			slot.setSlotNumber(slotNumber);
			inventory.put(slotNumber, slot);
		}
		
		return inventory;
	}

	public Map<String, Slot> getInventory() throws IOException {
		inventory = stockInventory();
		return inventory;
	}

}
