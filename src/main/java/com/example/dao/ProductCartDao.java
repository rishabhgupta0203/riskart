package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.ProductCart;
@Repository
public interface ProductCartDao extends JpaRepository<ProductCart, Integer>{

	@Query("SELECT p FROM ProductCart p WHERE p.id= :id")
	List<ProductCart>getproductcart(@Param("id") int id);
	
	/*
	 * @Query("SELECT p FROM productcart p WHERE p.= :id AND p.products_id= :productId"
	 * ) List<ProductCart>getproductcartId(@Param(""));
	 */
	
	
}
 