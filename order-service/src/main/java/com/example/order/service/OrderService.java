package com.example.order.service;

import com.example.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
	private final Map<String, Order> idToOrder = new ConcurrentHashMap<>();
	public List<Order> listAll() { return new ArrayList<>(idToOrder.values()); }
	public Order create(Order order) {
		if (order.getId() == null || order.getId().isBlank()) {
			order.setId(java.util.UUID.randomUUID().toString());
		}
		idToOrder.put(order.getId(), order);
		return order;
	}
}
