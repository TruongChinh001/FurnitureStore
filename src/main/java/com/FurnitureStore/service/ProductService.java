package com.FurnitureStore.service;

import java.util.List;

import com.FurnitureStore.model.Product;

public interface ProductService {

	List<Product> findAll();

	List<Product> findByCategory(Integer integer);

	List<Product> findByCategoryGroup(Integer integer);

	Product findById(Integer id);

	Integer getCountProducts();

	Integer getCountProductsByCategory(Integer integer);

	List<Product> getNewProduct();

	List<Product> getFeaturedProduct();

}
