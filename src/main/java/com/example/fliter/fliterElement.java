package com.example.fliter;

public class fliterElement {

	String color;
	int max;
	int min;
	String brand;

	public fliterElement(String color, int max, int min, String brand) {
		super();
		this.color = color;
		this.max = max;
		this.min = min;
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public fliterElement() {
		super();
		// TODO Auto-generated constructor stub
	}

}
