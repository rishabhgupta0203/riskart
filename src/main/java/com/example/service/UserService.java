package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.entity.Address;
import com.example.entity.User;
//Service class for handling operations related to users

@Service
public class UserService {
	@Autowired
private UserDao userdao;

    /**
     * Checks if the provided email and password match any user in the database.
     * 
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return True if a user with the provided email and password exists, otherwise false.
     */
	public boolean check(String email,String password)
	{
		
		List<User>person=userdao.checkEmailPassword(email, password);
		if(person.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	  /**
     * Finds users based on their email.
     * 
     * @param email The email of the user to find.
     * @return List of users with the specified email.
     */
	public List<User> findelementbyemail(String email)
	{
		return this.userdao.findElementByEmail(email);
	}

    /**
     * Registers a new user.
     * 
     * @param user The user to be registered.
     */
	public void signup(User user)
	{
		this.userdao.save(user);
	}
	 /**
     * Retrieves a user based on their ID.
     * 
     * @param id The ID of the user.
     * @return List containing the user with the specified ID.
     */
	public List<User> getElementById(int id)
	{
		List<User>person=userdao.findElementById(id);
		return person;
	}
	 /**
     * Updates user information based on their ID.
     * 
     * @param user The updated user information.
     */
	public void updateElementById(User user)
	{
		int id=user.getId();
		if(userdao.findElementById(id)!=null)
		{
		List<User> userr=	userdao.findElementById(id);
		  User user1=userr.get(0);
		  Address current_address=user.getAddress();
		  String street=current_address.getStreet();
		  String city=current_address.getCity();
		  String state=current_address.getState();
		  int pincode=current_address.getPincode();
		  Address address=user1.getAddress();
		  if(street==address.getStreet()&&city==address.getCity()&&pincode==address.getPincode()&&state==address.getState())
		  {
	      
		  }
		  else
		  {
			   user1.setAddress(user.getAddress());

		  }
		  
		   user1.setEmail(user.getEmail());
		   user1.setName(user.getName());
		   user1.setPassword(user.getPassword());
		   user1.setPhone(user.getPhone());
		   user1.setUsername(user.getUsername());
		   
		   userdao.save(user1);
		}
	}
	
	

}
