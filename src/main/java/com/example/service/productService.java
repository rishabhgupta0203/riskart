package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OrderDao;
import com.example.dao.ProductsDao;
import com.example.dao.UserDao;
import com.example.entity.Order;
import com.example.entity.OrderProduct;
import com.example.entity.Products;
import com.example.entity.User;
//Service class for handling operations related to products

@Service
public class productService {
    @Autowired
	ProductsDao productdao;
	@Autowired
	OrderDao orderdao;
	@Autowired
	public ProductsDao productsdao;
	@Autowired
	public UserDao userdao;
	/**
     * Adds a new product to the database.
     * 
     * @param product The product to be added.
     */
    public void add(Products product)
    {
    	productdao.save(product);
    }
    /**
     * Updates an existing product in the database.
     * 
     * @param product The updated product information.
     */
    public void update(Products product)
    {
    	 int id=product.getId();
    	 List<Products> initial_products=productdao.findElementById(id);
    	Products initial_product=initial_products.get(0);
    	 initial_product.setName(product.getName());
    	 initial_product.setCategory(product.getCategory());
    	 initial_product.setDetail(product.getDetail());
    	 initial_product.setPrice(product.getPrice());
         initial_product.setBrand(product.getBrand()); 
         initial_product.setColor(product.getColor());
    	 productdao.save(initial_product);
    	 
    }
    /**
     * Retrieves a list of all products in the database.
     * 
     * @return List of all products.
     */
    public List<Products> allProducts()
    {
    	return productdao.findAll();
    }
    /**
     * Retrieves a product based on its ID.
     * 
     * @param id The ID of the product.
     * @return The product with the specified ID.
     */
    public Products getProductById(int id)
    {
    	 List<Products> initial_products=productdao.findElementById(id);
    	Products product=initial_products.get(0);
    	return product;
    }

    /**
     * Retrieves a list of products based on their category.
     * 
     * @param category The category of the products.
     * @return List of products in the specified category.
     */
    public List<Products> getProductByCategory(String category)
    {
    	List<Products> element=productdao.findElementByCategory(category);
    	return element;
    }

    /**
     * Retrieves a list of products based on a search string.
     * 
     * @param searchString The search string to match against product names and details.
     * @return List of products matching the search string.
     */
    public List<Products> getProductBySearchstring(String searchString)
    {
    	List<Products> element=productdao.findProductsBySearchString(searchString);
    	return element;
    }
   

    /**
     * Retrieves a list of products within a price range and category.
     * 
     * @param min      The minimum price.
     * @param max      The maximum price.
     * @param category The category of the products.
     * @return List of products within the specified price range and category.
     */
    public List<Products> getbyprice(int min,int max,String category)
    {
    	return productdao.findProductByPrice(min, max,category);
    }
    /**
     * Retrieves a list of products within a price range, brand, and category.
     * 
     * @param min      The minimum price.
     * @param max      The maximum price.
     * @param brand    The brand of the products.
     * @param category The category of the products.
     * @return List of products within the specified price range, brand, and category.
     */
    public List<Products> getbypricebrand(int min,int max,String brand,String category)
    {
    	return productdao.findProductByPricebrand(min, max,brand,category);
    }
    /**
     * Retrieves a list of products within a price range, brand, color, and category.
     * 
     * @param min      The minimum price.
     * @param max      The maximum price.
     * @param brand    The brand of the products.
     * @param color    The color of the products.
     * @param category The category of the products.
     * @return List of products within the specified price range, brand, color, and category.
     */
    public List<Products> getbypricebrandcolor(int min,int max,String brand,String color,String category)
    {
    	return productdao.findProductByPricebrandcolor(min, max,brand,color,category);
    }
    /**
     * Retrieves a list of products within a price range, color, and category.
     * 
     * @param min      The minimum price.
     * @param max      The maximum price.
     * @param color    The color of the products.
     * @param category The category of the products.
     * @return List of products within the specified price range, color, and category.
     */
    public List<Products> getbypricecolor(int min,int max,String color,String category)
    {
    	return productdao.findProductByPricecolor(min, max,color,category);
    }
    /**
     * Handles the purchase of a product, creating an order for the specified user.
     * 
     * @param userId    The ID of the user making the purchase.
     * @param productId The ID of the product being purchased.
     * @param quantity  The quantity of the product being purchased.
     */
    public void buyNow(int UserId,int productId,int quantity)
    {
    	 Order order=new Order();
    	 User userr=(User) userdao.findElementById(UserId).get(0);
    		Products product=productsdao.findElementById(productId).get(0);
    		OrderProduct orderproduct= new OrderProduct();
    		orderproduct.setOrderstatus("confirmed");
    		orderproduct.setProduct(product);
    		orderproduct.setQuantity(quantity);
    		orderproduct.setTotalAmount(product.getPrice()*quantity);
    		order.setProduct(orderproduct);
    		order.setUser(userr);
    	    orderdao.save(order);
    }
    
    
}
