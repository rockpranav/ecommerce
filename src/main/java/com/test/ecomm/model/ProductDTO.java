package com.test.ecomm.model;

import org.springframework.stereotype.Component;

import com.test.ecomm.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductDTO {

	private Long id;
	private String name;
	private String code;
	private String description;
	private CategoryDTO categoryDTO;

	public ProductDTO(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.code = p.getCode();
		this.description = p.getDescription();
		//this.categoryDTO = new CategoryDTO(p.getCategory());
	}

}
