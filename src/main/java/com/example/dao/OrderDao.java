package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Order;
import com.example.entity.User;
@Repository
public interface OrderDao extends JpaRepository<Order, Integer>{

	@Query("SELECT o FROM Order o WHERE o.user= :user")
	List<Order>getAllOrder(@Param("user") User user);
	
}
