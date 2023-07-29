package com.test.ecomm.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.test.ecomm.model.CategoryDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

	@Id
	@GenericGenerator(name = "cat_gen", strategy  = "increment")
	@GeneratedValue(generator = "cat_gen")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Product> products ;
	
	public Category(CategoryDTO dto) {
		
		this.id=dto.getId();
		this.name=dto.getName();
		this.code=dto.getCode();
		this.description=dto.getDescription();
		//this.products = dto.getProductDTOs().stream().map(p-> new Product(p)).collect(Collectors.toList());
	}

}
