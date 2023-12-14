package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Products;
@Repository
public interface ProductsDao extends JpaRepository<Products, Integer>{

	
	@Query("SELECT u FROM Products u WHERE u.id= :id")
	List<Products> findElementById(@Param("id") int id);
	
	@Query("SELECT u FROM Products u WHERE u.category= :category")
	List<Products> findElementByCategory(@Param("category") String category);
	
	String str="SELECT p FROM Products p WHERE LOWER(p.category) LIKE LOWER(concat('%', :searchString, '%')) "
			+ "OR LOWER(p.detail) LIKE LOWER(concat('%', :searchString, '%')) "
			+ "OR LOWER(p.name) LIKE LOWER(concat('%', :searchString, '%'))  "
			+ "OR LOWER(p.color) LIKE LOWER(concat('%', :searchString, '%'))  "
			+"OR LOWER(p.brand) LIKE LOWER(concat('%', :searchString, '%'))  "
			;	
	@Query(str)
	    List<Products> findProductsBySearchString(@Param("searchString") String searchString);
	
		/*
		 * @Query("Select MAX(p.price) From Products Where u.category= :category")
		 * String findmax(@Param("category") String category);
		 */
	
	@Query("Select p FROM Products p WHERE p.category=:category AND p.price BETWEEN :min AND :max")
	List<Products>findProductByPrice(@Param("min") int min,@Param("max") int max,@Param("category") String category);
	
	@Query("Select p FROM Products p WHERE p.category=:category AND p.brand= :brand AND p.price BETWEEN :min AND :max")
	List<Products>findProductByPricebrand(@Param("min") int min,@Param("max") int max,@Param("brand") String brand,@Param("category") String category);
	
	@Query("Select p FROM Products p WHERE p.category=:category AND p.brand= :brand AND p.color= :color AND p.price BETWEEN :min AND :max")
	List<Products>findProductByPricebrandcolor(@Param("min") int min,@Param("max") int max,@Param("brand") String brand,@Param("color") String color,@Param("category") String category);
	
	@Query("Select p FROM Products p WHERE p.category=:category AND p.color= :color AND p.price BETWEEN :min AND :max")
	List<Products>findProductByPricecolor(@Param("min") int min,@Param("max") int max,@Param("color") String color,@Param("category") String category);
}
