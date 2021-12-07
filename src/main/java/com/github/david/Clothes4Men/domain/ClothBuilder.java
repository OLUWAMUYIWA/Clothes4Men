package com.github.david.Clothes4Men.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClothBuilder {
		
	private String title;
	private int stock;	
	private double price;
	private String picture;
	private List<String> sizes;
	private List<String> categories;
	private List<String> brands;
	
	public ClothBuilder() {
	}
	
	public ClothBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public ClothBuilder stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}
	
	public ClothBuilder withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public ClothBuilder imageLink(String picture) {
		this.picture = picture;
		return this;
	}
	
	public ClothBuilder sizesAvailable(List<String> sizes) {
		this.sizes = sizes;
		return this;
	}
	
	public ClothBuilder ofCategories(List<String> categories) {
		this.categories = categories;
		return this;
	}
	
	public ClothBuilder ofBrand(List<String> brands) {
		this.brands = brands;
		return this;
	}
	
	public Cloth build() {
		Cloth cloth = new Cloth();
		cloth.setTitle(this.title);
		cloth.setPrice(this.price);
		cloth.setStock(this.stock);
		cloth.setPicture(this.picture);		
		
		if (this.sizes != null && !this.sizes.isEmpty()) {
			Set<Size> sizeElements = new HashSet<>();
			for (String val : this.sizes) {
				sizeElements.add(new Size(val,cloth));
			}	
			cloth.setSizes(sizeElements);
		}
		
		if (this.categories != null && !this.categories.isEmpty() ) {
			Set<Category> catElements = new HashSet<>();
			for (String val : this.categories) {
				catElements.add(new Category(val,cloth));
			}
			cloth.setCategories(catElements);
		}		
		if (this.brands != null && !this.brands.isEmpty() ) {
			Set<Brand> brandlements = new HashSet<>();
			for (String val : this.brands) {
				brandlements.add(new Brand(val,cloth));
			}
			cloth.setBrands(brandlements);
		}		
		
		
		return cloth;
	}
	
}