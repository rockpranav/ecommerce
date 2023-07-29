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

import com.test.ecomm.model.CategoryDTO;
import com.test.ecomm.model.ProductDTO;
import com.test.ecomm.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public List<CategoryDTO> all(@RequestParam(name = "page", defaultValue = "1") int page)
	{
		return categoryService.all(page);
	}
	
	@PostMapping
	public CategoryDTO add(@RequestBody CategoryDTO dto) {
		return categoryService.add(dto);
	}
	
	@PutMapping("{di}")
	public CategoryDTO update(@RequestBody CategoryDTO dto, @PathVariable Long di) {
		return categoryService.update(dto, di);
	}
	
	
	@DeleteMapping("{di}")
	public void delete(@PathVariable long di) {
		categoryService.delete(di);
	}
	
	@GetMapping("{di}")
	public CategoryDTO getById(@PathVariable Long di) {
		return categoryService.getById(di);
	}
	
}
