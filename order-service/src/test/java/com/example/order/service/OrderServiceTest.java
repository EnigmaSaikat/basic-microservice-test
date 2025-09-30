package com.example.order.service;

import com.example.order.model.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
	@Test
	void create_and_listAll() {
		OrderService service = new OrderService();
		Order created = service.create(new Order(null, "p1", 2));
		assertThat(created.getId()).isNotBlank();
		assertThat(service.listAll()).hasSize(1);
	}
}
