package com.test.ecomm.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.test.ecomm.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@AllArgsConstructor
public class CategoryDTO {

	private Long id;
	private String name;
	private String code;
	private String description;
	
	private List<ProductDTO> productDTOs;

	public CategoryDTO(Category c) {
		this.id = c.getId();
		this.name = c.getName();
		this.code = c.getCode();
		this.description = c.getDescription();
		if(null != c.getProducts())
			this.productDTOs = c.getProducts().stream().map(p-> new ProductDTO(p)).collect(Collectors.toList());
	}

}
