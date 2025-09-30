package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
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

@WebMvcTest(ProductController.class)
class ProductControllerTest {
	@Autowired MockMvc mockMvc;
	@MockBean ProductService productService;

	@Test
	void listAll_returnsOk() throws Exception {
		when(productService.listAll()).thenReturn(List.of(new Product("1","A",2)));
		mockMvc.perform(get("/api/products"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].id").value("1"))
			.andExpect(jsonPath("$[0].name").value("A"))
			.andExpect(jsonPath("$[0].quantity").value(2));
	}
}
