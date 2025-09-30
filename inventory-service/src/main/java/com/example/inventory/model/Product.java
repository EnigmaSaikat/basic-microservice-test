package com.example.inventory.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Product {
	private String id;
	@NotBlank
	private String name;
	@Min(0)
	private int quantity;

	public Product() {}

	public Product(String id, String name, int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
}
