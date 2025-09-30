package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;
	public ProductController(ProductService productService) { this.productService = productService; }
	@GetMapping
	public List<Product> listAll() { return productService.listAll(); }
	@GetMapping("/{id}")
	public Product getById(@PathVariable String id) { return productService.getById(id); }
	@PostMapping
	public ResponseEntity<Product> create(@Validated @RequestBody Product product) {
		Product created = productService.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) { productService.delete(id); }
}
