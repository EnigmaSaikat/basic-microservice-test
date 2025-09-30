package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
	@Autowired MockMvc mockMvc;
	@MockBean OrderService orderService;

	@Test
	void listAll_returnsOk() throws Exception {
		when(orderService.listAll()).thenReturn(List.of(new Order("1","p1",2)));
		mockMvc.perform(get("/api/orders"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].id").value("1"))
			.andExpect(jsonPath("$[0].productId").value("p1"))
			.andExpect(jsonPath("$[0].quantity").value(2));
	}
}
