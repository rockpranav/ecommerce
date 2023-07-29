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
public class CategoryServiceImpl implements CategoryService {

	private static final int PAGE_SIZE = 10;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ProductRepo productRepo;

	@Override
	public List<CategoryDTO> all(int page) {

		Pageable pageable = PageRequest.of(page, PAGE_SIZE);
		Page<Category> pg = categoryRepo.findAll(pageable);
		List<Category> categories = pg.toList();
		return categories.stream().map(c -> new CategoryDTO(c)).collect(Collectors.toList());
	}

	@Transactional
	public CategoryDTO add(CategoryDTO dto) {
		Category c = categoryRepo.save(new Category(dto));
		if (null != dto.getProductDTOs() & dto.getProductDTOs().size() > 0) {
			for (ProductDTO pdto : dto.getProductDTOs()) {
				Product p = new Product(pdto);
				p.setCategory(c);
				productRepo.save(p);
			}
		}

		Optional<Category> opt =  categoryRepo.findById(c.getId());
		return new CategoryDTO(opt.get());
	}

	@Transactional
	public void delete(long id) {
		Optional<Category> opt = categoryRepo.findById(id);
		if(opt.isPresent())
			categoryRepo.delete(opt.get());
	}

	@Transactional
	public CategoryDTO update(CategoryDTO dto, Long id) {

		Optional<Category> co = categoryRepo.findById(id);
		if (co.isPresent()) {
			Category c = co.get();
			c.setCode(dto.getCode());
			c.setDescription(dto.getDescription());
			c.setName(dto.getName());

			if (null != dto.getProductDTOs() && dto.getProductDTOs().size() > 0) {
				for (ProductDTO pdto : dto.getProductDTOs()) {
					if (null == pdto.getId()) {
						Product np = new  Product(pdto);
						np.setCategory(c);
						productRepo.save(np);
					} else {
						Optional<Product> po = productRepo.findById(pdto.getId());
						if (po.isPresent()) {
							Product p = po.get();
							p.setCategory(c);
							p.setCode(pdto.getCode());
							p.setDescription(pdto.getDescription());
							p.setName(pdto.getName());
							productRepo.save(p);
						}
					}
				}
			}

			Optional<Category> opt = categoryRepo.findById(id);

			if (opt.isPresent())
				return new CategoryDTO(opt.get());
		}

		return null;
	}

	@Override
	public CategoryDTO getById(Long di) {
		Optional<Category> opt = categoryRepo.findById(di);
		return (opt.isPresent()) ? new CategoryDTO(opt.get()) : null;
	}

}
