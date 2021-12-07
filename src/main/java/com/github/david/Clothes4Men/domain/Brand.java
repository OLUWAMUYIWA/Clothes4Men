package com.github.david.Clothes4Men.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="cloth_id")
	private Cloth cloth;
	
	private String name;
	
	public Brand() {
	}
	
	public Brand(String name, Cloth cloth) {
		this.name = name;
		this.cloth = cloth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cloth getCloth() {
		return cloth;
	}

	public void setCloth(Cloth cloth) {
		this.cloth = cloth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
