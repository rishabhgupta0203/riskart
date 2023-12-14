package com.example.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Products;
import com.example.fliter.fliterElement;
import com.example.service.productService;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
     @Autowired
	productService productservice;
     
     @PostMapping("/addProduct")
     void addproduct(@RequestBody Products product)
     {
    	 
    	 productservice.add(product);
     }
     
     @PostMapping("/update")
     void update(@RequestBody Products product)
     {
         productservice.update(product);
     }
     
     @GetMapping("/getById/{productId}")
     Products getproduct(@PathVariable int productId)
     {
    	 return productservice.getProductById(productId);
    	 
     }
     
     @GetMapping("/{category}")
     List<Products> ProductByCategory(@PathVariable String category)
     {
    	 return productservice.getProductByCategory(category);
     }
     
     @GetMapping("/search/{searchString}")
     List<Products>ProductByString(@PathVariable String searchString)
     {
    	 return productservice.getProductBySearchstring(searchString);
     }
     
     @GetMapping("/getAllProducts")
     List<Products>allProduct()
     {
    	 return productservice.allProducts();
     }
     
     
     @PostMapping("/{category}/getFilteredProducts")
     List<Products>FilteredProducts(@PathVariable String category,@RequestBody fliterElement filter)
		{

			int min;
			int max;
			String color, brand;
			color = filter.getColor();
			brand = filter.getBrand();
			min = filter.getMin();
			max = filter.getMax();
			if (color == "") {
				if (brand == "") {
					return productservice.getbyprice(min, max,category);

				} else {
					return productservice.getbypricebrand(min, max, brand,category);
				}
			} else {

				if (brand == "") {
					return productservice.getbypricecolor(min, max, color,category);

				} else {
					return productservice.getbypricebrandcolor(min, max, brand, color,category);
				}

			}

		}
     @PostMapping("/{userId}/BuyNow/{productId}/{quantity}")
     public void buyNow(@PathVariable int userId,@PathVariable int productId,@PathVariable int quantity)
     {
    	productservice.buyNow(userId, productId, quantity);
     }
}
