package com.github.david.Clothes4Men.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.david.Clothes4Men.domain.Cloth;

public interface ClothService {

	List<Cloth> findAllCloths();
	
	Page<Cloth> findClothsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands, String search);
		
	List<Cloth> findFirstCloths();

	Cloth findClothById(Long id);
	
	Cloth saveCloth(Cloth cloth);

	void deleteClothById(Long id);
	
	List<String> getAllSizes();

	List<String> getAllCategories();

	List<String> getAllBrands();

}
