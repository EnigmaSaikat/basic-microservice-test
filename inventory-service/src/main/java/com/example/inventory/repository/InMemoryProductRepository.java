package com.example.inventory.repository;

import com.example.inventory.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository {
	private final Map<String, Product> idToProduct = new ConcurrentHashMap<>();

	public Optional<Product> findById(String id) { return Optional.ofNullable(idToProduct.get(id)); }
	public List<Product> findAll() { return new ArrayList<>(idToProduct.values()); }
	public Product save(Product product) {
		if (product.getId() == null || product.getId().isBlank()) {
			product.setId(java.util.UUID.randomUUID().toString());
		}
		idToProduct.put(product.getId(), product);
		return product;
	}
	public boolean deleteById(String id) { return idToProduct.remove(id) != null; }
}
