package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;
	public OrderController(OrderService orderService) { this.orderService = orderService; }
	@GetMapping
	public List<Order> listAll() { return orderService.listAll(); }
	@PostMapping
	public ResponseEntity<Order> create(@Validated @RequestBody Order order) {
		Order created = orderService.create(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
}
