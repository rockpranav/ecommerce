package com.test.ecomm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.test.ecomm.model.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GenericGenerator(name = "product_gen", strategy  = "increment")
	@GeneratedValue(generator = "product_gen")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public Product(ProductDTO dto) {
	
		this.id =dto.getId();
		this.name=dto.getName();
		this.code=dto.getCode();
		this.description = dto.getDescription();
		//this.category = new Category(dto.getCategoryDTO());
	}
}
