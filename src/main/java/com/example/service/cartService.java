package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CartDao;
import com.example.dao.ProductCartDao;
import com.example.dao.ProductsDao;
import com.example.dao.UserDao;
import com.example.entity.Cart;
import com.example.entity.ProductCart;
import com.example.entity.Products;
import com.example.entity.User;
//Service class for handling operations related to the user's shopping cart

@Service
public class cartService {
 
	@Autowired
	public CartDao cartdao;
	@Autowired
	public ProductCartDao productcartdao;
	@Autowired
	public ProductsDao productsdao;
	@Autowired
	public UserDao userdao;
	
	
	/*
	 * public List<Products> getCartById(int UserId,int cartitemId) { return
	 * cartdao.cartById(UserId,cartitemId); }
	 * 
	 * public List<ProductCart>getProductsByCart(int UserId) { return
	 * cartdao.productByCart(UserId); }
	 */
	/**
	 * Retrieves the user's cart by user ID.
	 * 
	 * @param userId The ID of the user.
	 * @return List of cart items associated with the user.
	 */
	public List<Cart>getCartById(int UserId)
	{
		User userr=(User) userdao.findElementById(UserId).get(0);
		return cartdao.getCart(userr);
	}
	/**
	 * Retrieves products from a specific cart item by user ID and cart item ID.
	 * 
	 * @param userId      The ID of the user.
	 * @param cartItemId  The ID of the cart item.
	 * @return List of products in the specified cart item.
	 */
	public List<ProductCart>getproducts(int UserId,int cartitemId)
	{		User userr=(User) userdao.findElementById(UserId).get(0);
   
		return  cartdao.getProductcartByUserAndCardId(userr, cartitemId);
	
	}
	/**
	 * Adds a product to the user's cart.
	 * 
	 * @param userId    The ID of the user.
	 * @param productId The ID of the product.
	 * @param quantity  The quantity of the product to be added.
	 */
	public void getproductCart(int UserId,int productId,int quantity)
	{User userr=(User) userdao.findElementById(UserId).get(0);
		List<ProductCart>listId=cartdao.getproductcartByUser(userr);
		Products product=productsdao.findElementById(productId).get(0);
		boolean t=false;
		for(ProductCart productcarts:listId)
		{
			if(productcarts.getProduct()==product)
			{t=true;
			int quant=productcarts.getQuantity();
			productcarts.setQuantity(quant+quantity);
			productcartdao.save(productcarts);
			break;
			}
			/*
			 * if(productcartdao.getproductcartId(product,productcarts)!=null) { t=true;
			 * List<ProductCart>item=productcartdao.getproductcart(Id); ProductCart
			 * updateItem=item.get(0); int count=updateItem.getQuantity();
			 * updateItem.setQuantity(count+1);
			 * 
			 * }
			 */
		}
		if(!t)
		{
			ProductCart newCart = new ProductCart();
			newCart.setProduct(product);
			newCart.setQuantity(quantity);
			
			Cart cart=new Cart();
			
		
			
			cart.setUser(userr);
		
			cart.setSingle_item(newCart);
			
			cartdao.save(cart);
			
		}
	}
	/**
	 * Removes a product from the user's cart.
	 * 
	 * @param userId    The ID of the user.
	 * @param productId The ID of the product to be removed.
	 */
	public void removecart(int UserId,int productId) 
	{User userr=(User) userdao.findElementById(UserId).get(0);
	Products product=productsdao.findElementById(productId).get(0);
	List<ProductCart>listId=cartdao.getproductcartByUser(userr);
	List<Cart>Cartforthisuser;
	for(ProductCart productcarts:listId)
	{
		if(productcarts.getProduct()==product)
		{
			Cartforthisuser=cartdao.removeInCart(userr, productcarts);
			for(Cart cart:Cartforthisuser)
			{
				cartdao.delete(cart);
			}
			break;
		
		}}
	
	}
	/**
	 * Changes the quantity of a product in the user's cart.
	 * 
	 * @param userId    The ID of the user.
	 * @param productId The ID of the product.
	 */
	public void changequant(int UserId,int productId)
	{
		User userr=(User) userdao.findElementById(UserId).get(0);
		Products product=productsdao.findElementById(productId).get(0);
		List<ProductCart>listId=cartdao.getproductcartByUser(userr);
		
		for(ProductCart productcarts:listId)
		{
			if(productcarts.getProduct()==product)
			{
				if(productcarts.getQuantity()>1)
				{
					int x=productcarts.getQuantity();
					productcarts.setQuantity(x-1);
					productcartdao.save(productcarts);
				}
				else
				{	List<Cart>Cartforthisuser;
					Cartforthisuser=cartdao.removeInCart(userr, productcarts);
					for(Cart cart:Cartforthisuser)
					{
						cartdao.delete(cart);
					}
				}
				break;
			
			}}
	}
	
	
	
}
