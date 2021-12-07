package com.github.david.Clothes4Men.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Size implements Comparable<Size> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cloth_id")
	private Cloth cloth;	
	private String value;
		
	public Size() {}
	
	public Size(String value, Cloth cloth) {
		this.value = value;
		this.cloth = cloth;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Cloth getCloth() {
		return cloth;
	}
	public void setCloth(Cloth cloth) {
		this.cloth = cloth;
	}

	@Override
	public int compareTo(Size s) {
		return this.value.compareTo(s.getValue());		
	}
	
	
	
}
