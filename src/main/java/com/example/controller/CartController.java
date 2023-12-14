package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Cart;
import com.example.entity.ProductCart;
import com.example.service.cartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

	@Autowired
	cartService cartservice;
	
	@GetMapping("/{userId}/getCart")
	List<Cart> getcart(@PathVariable int userId)
	{
		return cartservice.getCartById(userId);
	}
	
	@GetMapping("/{userId}/getCartItem/{cartitemId}")
	List<ProductCart> getcartitems(@PathVariable int userId,@PathVariable int cartitemId)
	{
		return cartservice.getproducts(userId, cartitemId);
	}
	
	@GetMapping("/{userId}/add/{productId}/{quantity}")
	void addToCart(@PathVariable int userId,@PathVariable int productId,@PathVariable int quantity)
	{
		cartservice.getproductCart(userId, productId,quantity);
	}
	
	@GetMapping("/{userId}/remove/{productId}")
	void remove(@PathVariable int userId,@PathVariable int productId)
	{
		cartservice.removecart(userId, productId);
	}
	
	@PostMapping("/{userId}/changeQuantity/{productId}")
	void changeqantity(@PathVariable int userId,@PathVariable int productId)
	{
		cartservice.changequant(userId, productId);
	}
}
