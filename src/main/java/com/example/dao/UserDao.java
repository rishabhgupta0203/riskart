package com.example.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	/*
	 * @Query("SELECT * FROM User WHERE email= :email and password= :password")
	 * List<User>checkEmailPassword(@Param("email") String email,@Param("password")
	 * String password);
	 */
	@Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    List<User> checkEmailPassword(@Param("email") String email, @Param("password") String password);
	
	@Query("SELECT u FROM User u WHERE u.id= :id")
	List<User>findElementById(@Param("id") int id);
	
	@Query("SELECT u FROM User u WHERE u.email= :email")
	List<User>findElementByEmail(@Param("email") String email);
	
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query("DELETE FROM User u WHERE u.id= :id")
	 * List<User>deleteElementById(@Param("id") int id);
	 */
	
}
