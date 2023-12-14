package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Cart;
import com.example.entity.ProductCart;
import com.example.entity.User;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{

	/*
	 * @Query("SELECT p FROM Products p WHERE p.id IN (SELECT u.Product_id FROM ProductCart u WHERE u.id = :cartitemId "
	 * ) List<Products>cartById(@Param("UserId") int UserId,@Param("cartitemId") int
	 * cartitemId);
	 * 
	 * @Query("SELECT p FROM ProductCart p WHERE p.id IN (SELECT c.ProductCart_id FROM Cart c WHERE c.User_id = :UserId))"
	 * ) List<ProductCart>productByCart(@Param("UserId") int UserId);
	 */
	@Query("SELECT c FROM Cart c WHERE c.user= :user")
	List<Cart>getCart(@Param("user") User user);
	
	@Query("SELECT c.single_item FROM Cart c WHERE c.user= :UserId AND c.id= :cartitemId")
	List<ProductCart>getProductcartByUserAndCardId(@Param("UserId") User user,@Param("cartitemId") int cartitemId);
	
	@Query("SELECT c.single_item FROM Cart c WHERE c.user= :UserId")
	List<ProductCart>getproductcartByUser(@Param("UserId") User UserId);
	@Modifying
	@Query("SELECT c FROM Cart c WHERE c.user= :User AND c.single_item= :productcart")
	List<Cart>removeInCart(@Param("User") User user,@Param("productcart") ProductCart productcart);
}
