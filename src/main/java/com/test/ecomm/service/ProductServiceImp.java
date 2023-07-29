package com.test.ecomm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.ecomm.entity.Category;
import com.test.ecomm.entity.Product;
import com.test.ecomm.model.CategoryDTO;
import com.test.ecomm.model.ProductDTO;
import com.test.ecomm.repo.CategoryRepo;
import com.test.ecomm.repo.ProductRepo;


@Service
public class ProductServiceImp implements ProductService {
	
	private static final int PAGE_SIZE = 10; 
	
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Transactional
	public List<ProductDTO> all(int page) {
		Pageable pageable = PageRequest.of(page, PAGE_SIZE);
		Page<Product> pg =  productRepo.findAll(pageable);
		List<Product> product = pg.toList();//productRepo.findAll();
		return product.stream().map(p -> new ProductDTO(p.getId(), p.getName(), p.getCode(), p.getDescription(),
				new CategoryDTO(p.getCategory()))).collect(Collectors.toList());
	}

	@Transactional
	public ProductDTO add(ProductDTO dto) {
		Product p = productRepo.save(new Product(dto));
		categoryRepo.save(new Category(dto.getCategoryDTO()));
		Optional<Product> opt = productRepo.findById(p.getId());
		return (opt.isPresent()) ? new ProductDTO(opt.get()) : null;
	}

	@Transactional
	public void delete(long id) {
		categoryRepo.deleteById(id);
	}

	@Transactional
	public ProductDTO update(ProductDTO dto, Long id) {
		Optional<Product> opt = productRepo.findById(id);
		if (opt.isPresent()) {
			Product p = opt.get();
			p.setName(dto.getName());
			p.setCode(dto.getCode());
			p.setDescription(dto.getDescription());

			if (null != dto.getCategoryDTO()) {
				if (null == dto.getCategoryDTO().getId()) {
					Category cat = new Category(dto.getCategoryDTO());
					categoryRepo.save(cat);
				} else {
					Optional<Category> aopt = categoryRepo.findById(dto.getCategoryDTO().getId());
					if (aopt.isPresent()) {
						Category tCategory = aopt.get();
						tCategory = aopt.get();
						tCategory.setName(dto.getCategoryDTO().getName());
						tCategory.setCode(dto.getCategoryDTO().getCode());
						tCategory.setDescription(dto.getCategoryDTO().getDescription());
						categoryRepo.save(tCategory);
					}
				}
			}

			Product updated = productRepo.save(p);
			return new ProductDTO(updated);
		}
		return null;
	}

	@Override
	public ProductDTO getById(Long di) {
		Optional<Product> o =  productRepo.findById(di);
		if(o.isPresent())
			return new ProductDTO(o.get());
		
		return null;
	}
}
