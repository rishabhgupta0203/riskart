package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CartDao;
import com.example.dao.OrderDao;
import com.example.dao.UserDao;
import com.example.entity.Cart;
import com.example.entity.Order;
import com.example.entity.OrderProduct;
import com.example.entity.ProductCart;
import com.example.entity.Products;
import com.example.entity.User;
//Service class for handling operations related to user orders

@Service
public class orderService {
	@Autowired
	public CartDao cartdao;
	@Autowired
	UserDao userdao;
	@Autowired
	OrderDao orderdao;

	/**
	 * Retrieves the orders associated with a specific user.
	 * 
	 * @param userId The ID of the user.
	 * @return List of orders for the user.
	 */
	public List<Order> getorders(int UserId)
	{
		User userr=(User) userdao.findElementById(UserId).get(0);
		return orderdao.getAllOrder(userr);
	}
	/**
	 * Creates an order for a specific user based on the contents of their shopping cart.
	 * 
	 * @param userId The ID of the user.
	 */
	public void createOrder(int UserId)
	{
		User userr=(User) userdao.findElementById(UserId).get(0);
		List<ProductCart>listId=cartdao.getproductcartByUser(userr);
		
		for(ProductCart productcarts:listId)
		{
			int quantity=productcarts.getQuantity();
			Products product=productcarts.getProduct();
			OrderProduct orderproduct=new OrderProduct();
			String orderstatus="confirmed";
			int amount=productcarts.getQuantity()*product.getPrice();
			orderproduct.setProduct(product);
			orderproduct.setTotalAmount(amount);
			orderproduct.setOrderstatus(orderstatus);
			orderproduct.setQuantity(quantity);
			Order order=new Order();
			order.setProduct(orderproduct);
			order.setUser(userr);
			orderdao.save(order);
			List<Cart>Cartforthisuser;
			Cartforthisuser=cartdao.removeInCart(userr, productcarts);
			for(Cart cart:Cartforthisuser)
			{
				cartdao.delete(cart);
			}
			
		}
	
	}
}
