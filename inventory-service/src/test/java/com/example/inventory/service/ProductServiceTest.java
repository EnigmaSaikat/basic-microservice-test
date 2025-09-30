package com.example.inventory.service;

import com.example.inventory.model.Product;
import com.example.inventory.repository.InMemoryProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	@Mock InMemoryProductRepository repository;
	@InjectMocks ProductService service;

	@Test
	void listAll_returnsProducts() {
		when(repository.findAll()).thenReturn(List.of(new Product("1","A",2)));
		assertThat(service.listAll()).hasSize(1).first().extracting(Product::getName).isEqualTo("A");
	}

	@Test
	void getById_found() {
		when(repository.findById("1")).thenReturn(Optional.of(new Product("1","A",2)));
		Product p = service.getById("1");
		assertThat(p.getName()).isEqualTo("A");
	}

	@Test
	void getById_notFound() {
		when(repository.findById("x")).thenReturn(Optional.empty());
		assertThrows(NoSuchElementException.class, () -> service.getById("x"));
	}

	@Test
	void create_saves() {
		Product toSave = new Product(null,"A",1);
		Product saved = new Product("1","A",1);
		when(repository.save(toSave)).thenReturn(saved);
		Product result = service.create(toSave);
		assertThat(result.getId()).isEqualTo("1");
	}

	@Test
	void delete_existing() {
		when(repository.deleteById("1")).thenReturn(true);
		service.delete("1");
		verify(repository).deleteById("1");
	}

	@Test
	void delete_missing() {
		when(repository.deleteById("x")).thenReturn(false);
		assertThrows(NoSuchElementException.class, () -> service.delete("x"));
	}
}
