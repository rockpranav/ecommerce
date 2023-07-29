package com.test.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.ecomm.model.ProductDTO;
import com.test.ecomm.service.ProductService;

import lombok.Data;

@RestController
@RequestMapping("/products/")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ProductDTO> all(@RequestParam(name = "page", defaultValue = "1") int page) {
		return productService.all(page);

	}

	@PostMapping
	public ProductDTO add(@RequestBody ProductDTO dto) {
		return productService.add(dto);

	}

	@GetMapping("{di}")
	public ProductDTO getById(@PathVariable Long di) {
		return productService.getById(di);
	}

	@PutMapping("{di}")
	public ProductDTO update(@RequestBody ProductDTO dto, @PathVariable Long di) {
		return productService.update(dto, di);
	}

	@DeleteMapping("{di}")
	public void delete(@PathVariable long di) {
		productService.delete(di);
	}
}
