 package com.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    @OneToOne(cascade= CascadeType.ALL)
    private ProductCart single_item;
  
    @ManyToOne
    private User user;
    
    public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, ProductCart single_item, User user) {
		super();
		this.id = id;
		this.single_item = single_item;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductCart getSingle_item() {
		return single_item;
	}

	public void setSingle_item(ProductCart single_item) {
		this.single_item = single_item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
}
