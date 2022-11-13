package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Product;
import com.FurnitureStore.repository.ProductRepository;
import com.FurnitureStore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository repo;

	@Override
	public List<Product> findByCategoryGroup(Integer id) {
		return repo.findByCategoryGroup(id);
	}

	@Override
	public Product findById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public Integer getCountProducts() {
		return repo.getCountProducts();
	}
	
	@Override
	public Integer getCountProductsByKeyword(String keyword) {
		return repo.getCountProductsByKeyword(keyword);
	}

	@Override
	public Integer getCountProductsByCategory(Integer id) {
		return repo.getCountProductByCategory(id);
	}
	
	@Override
	public Integer getCountProductsByBrand(Integer id) {
		return repo.getCountProductByBrand(id);
	}

	@Override
	public List<Product> getNewProduct() {
		return repo.getNewProduct();
	}

	@Override
	public List<Product> getFeaturedProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsInCategoryGroup(int i) {
		return repo.getProductsInCategoryGroup(i);
	}

	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Product> listByPageable(int pageNum, String keyword) {

		Pageable pageable = PageRequest.of(pageNum - 1, 8);

		if (keyword != null) {
			return repo.findByKeywords(keyword, pageable);
		}

		return repo.findAll(pageable);
	}

	@Override
	public Page<Product> listByCategory(Integer pageNum, Integer cid) {
		Pageable pageable = PageRequest.of(pageNum - 1, 8);
		return repo.findAllByCategoryId(cid, pageable);
	}

	@Override
	public Page<Product> listByBrand(Integer pageNum, Integer bid) {
		Pageable pageable = PageRequest.of(pageNum - 1, 8);
		return repo.findAllByBrandId(bid, pageable);
	}

	@Override
	public Product create(Product product) {
		return repo.save(product);
	}
	
}
