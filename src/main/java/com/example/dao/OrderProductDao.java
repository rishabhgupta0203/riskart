package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.entity.OrderProduct;
@Repository
public interface OrderProductDao extends JpaRepository<OrderProduct, Integer>{

}
