package com.github.david.Clothes4Men.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cloth {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private int stock;	
	private double price;
	private String picture;
	
	@OneToMany(mappedBy="cloth", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Size> sizes;
	
	@OneToMany(mappedBy="cloth", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Brand> brands;
	
	@OneToMany(mappedBy="cloth", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Category> categories;

	public Cloth() {
	}
	
	public boolean hasStock(int amount) {
		return (this.getStock() > 0) && (amount <= this.getStock());
	}
	
	public void decreaseStock(int amount) {
		this.stock -= amount;
	}
	

	public void addSize(Size size) {
        sizes.add(size);
        size.setCloth(this);
    }
 
    public void removeSize(Size size) {
        sizes.remove(size);
        size.setCloth(null);
    }
    
	public void addCategory(Category category) {
        categories.add(category);
        category.setCloth(this);
    }
 
    public void removeCategory(Category category) {
    	categories.remove(category);
        category.setCloth(null);
    }
    
	public void addSize(Brand brand) {
        brands.add(brand);
        brand.setCloth(this);
    }
 
    public void removeSize(Brand brand) {
    	brands.remove(brand);
        brand.setCloth(null);
    }
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Set<Size> getSizes() {
		return sizes;
	}
	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}
	public Set<Brand> getBrands() {
		return brands;
	}
	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}
