package com.FurnitureStore.service.impl;

import java.util.List;

import com.FurnitureStore.model.Product;
import com.FurnitureStore.repository.ProductRepository;
import com.FurnitureStore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository repo;

	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}
	
	@Override
	public List<Product> findByCategory(Integer cid) {
		return repo.findByCategory(cid);
	}

	@Override
	public List<Product> findByCategoryGroup(Integer id) {
		return repo.findByCategoryGroup(id);
	}
	
	@Override
	public List<Product> findByBrand(Integer id) {
		return repo.findByBrand(id);
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
	
}
