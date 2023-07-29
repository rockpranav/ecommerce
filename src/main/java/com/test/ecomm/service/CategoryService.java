package com.test.ecomm.service;

import java.util.List;

import com.test.ecomm.model.CategoryDTO;
import com.test.ecomm.model.ProductDTO;

public interface CategoryService {

	List<CategoryDTO> all(int page);

	CategoryDTO add(CategoryDTO dto);

	void delete(long id);

	CategoryDTO update(CategoryDTO dto, Long id);
	
	public CategoryDTO getById(Long di);
}
