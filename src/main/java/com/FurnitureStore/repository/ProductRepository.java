package com.FurnitureStore.repository;

import java.util.List;

import com.FurnitureStore.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	List<Product> findByCategory(Integer cid);

	@Query("SELECT p FROM Product p WHERE p.category.categoryGroup.id = ?1")
	List<Product> findByCategoryGroup(Integer id);
	
	@Query("SELECT p FROM Product p WHERE p.brand.id = ?1")
	List<Product> findByBrand(Integer id);

	@Query("SELECT COUNT(p) FROM Product p")
	Integer getCountProducts();

	@Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = ?1")
	Integer getCountProductByCategory(Integer id);

	@Query("SELECT COUNT(p) FROM Product p WHERE p.brand.id = ?1")
	Integer getCountProductByBrand(Integer id);

//	@Query(value = "SELECT * FROM products WHERE create_date BETWEEN NOW() - INTERVAL 15 DAY AND NOW()", nativeQuery = true)
    @Query(value = "SELECT * FROM Products ORDER BY create_date LIMIT 8", nativeQuery = true)
	List<Product> getNewProduct();

    @Query(value = "SELECT * FROM Products p \r\n"
    		+ "INNER JOIN Categories c\r\n"
    		+ "ON p.category_id = c.id\r\n"
    		+ "INNER JOIN Categories_group cg\r\n"
    		+ "ON c.category_group_id = cg.id\r\n"
    		+ "WHERE cg.id = ?1 LIMIT 4", nativeQuery = true)
	List<Product> getProductsInCategoryGroup(int i);

}
