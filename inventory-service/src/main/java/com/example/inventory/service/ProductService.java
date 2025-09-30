package com.example.inventory.service;

import com.example.inventory.model.Product;
import com.example.inventory.repository.InMemoryProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
	private final InMemoryProductRepository repository;
	public ProductService(InMemoryProductRepository repository) { this.repository = repository; }
	public List<Product> listAll() { return repository.findAll(); }
	public Product getById(String id) {
		return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found: " + id));
	}
	public Product create(Product product) { return repository.save(product); }
	public void delete(String id) {
		boolean removed = repository.deleteById(id);
		if (!removed) { throw new NoSuchElementException("Product not found: " + id); }
	}
}
