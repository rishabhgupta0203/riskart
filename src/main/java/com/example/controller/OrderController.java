package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Order;
import com.example.service.orderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
	@Autowired
	orderService orderservice;
	
	@GetMapping("/{userId}/getOrders")
	public List<Order> getorder(@PathVariable int userId)
	{
		return orderservice.getorders(userId);
	}
	
	@GetMapping("/{userId}/createOrder")
	public void createOrder(@PathVariable int userId)
	{
		orderservice.createOrder(userId);
	}
	
	

}
