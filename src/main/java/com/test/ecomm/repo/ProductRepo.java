package com.test.ecomm.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.ecomm.entity.Product;


public interface ProductRepo extends JpaRepository<Product, Long> {
	Page<Product> findAll(Pageable pageable);
}