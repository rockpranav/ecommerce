package com.test.ecomm.service;

import java.util.List;

import com.test.ecomm.model.ProductDTO;

public interface ProductService {
	List<ProductDTO> all(int page);

	ProductDTO add(ProductDTO dto);

	void delete(long id);

	ProductDTO update(ProductDTO dto, Long id);
	
	public ProductDTO getById(Long di);
}
