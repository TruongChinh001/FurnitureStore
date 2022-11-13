package com.FurnitureStore.repository;

import java.util.List;

import com.FurnitureStore.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category.categoryGroup.id = ?1")
	List<Product> findByCategoryGroup(Integer id);

	@Query("SELECT COUNT(p) FROM Product p")
	Integer getCountProducts();
	
	@Query("SELECT COUNT(p) FROM Product p WHERE CONCAT(p.name, ' ' , p.category.name, ' ' , p.brand.name) LIKE %?1%")
	Integer getCountProductsByKeyword(String keyword);

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

    @Query("SELECT p FROM Product p WHERE CONCAT(p.id , ' ' , p.name , ' ' , p.category.name , ' ' , p.brand.name) LIKE %?1%")
	Page<Product> findByKeywords(String keyword, Pageable pageable);
    
    public Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);

    public Page<Product> findAllByBrandId(Integer bid, Pageable pageable);


}
