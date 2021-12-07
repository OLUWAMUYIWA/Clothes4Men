package com.github.david.Clothes4Men.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.david.Clothes4Men.domain.Cloth;
import com.github.david.Clothes4Men.repository.ClothRepository;
import com.github.david.Clothes4Men.repository.ClothSpecification;
import com.github.david.Clothes4Men.service.ClothService;

@Service
@Transactional
public class ClothServiceImpl implements ClothService {

	@Autowired
	private ClothRepository clothRepository;
	
	@Value("${clothservice.featured-items-number}")
	private int featuredClothsNumber;

	@Override
	public List<Cloth> findAllCloths() {
		return (List<Cloth>) clothRepository.findAllEagerBy();
	}
	
	@Override
	public Page<Cloth> findClothsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, 
										List<String> sizes, List<String> categories, List<String> brands, String search) {		
		Page<Cloth> page = clothRepository.findAll(ClothSpecification.filterBy(priceLow, priceHigh, sizes, categories, brands, search), pageable);
        return page;		
	}	
	
	@Override
	public List<Cloth> findFirstCloths() {
		return clothRepository.findAll(PageRequest.of(0,featuredClothsNumber)).getContent(); 
	}

	@Override
	public Cloth findClothById(Long id) {
		Optional<Cloth> opt = clothRepository.findById(id);
		return opt.get();
	}

	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public Cloth saveCloth(Cloth cloth) {
		return clothRepository.save(cloth);
	}
	
	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public void deleteClothById(Long id) {
		clothRepository.deleteById(id);		
	}

	
	@Override
	@Cacheable("sizes")
	public List<String> getAllSizes() {
		return clothRepository.findAllSizes();
	}

	@Override
	@Cacheable("categories")
	public List<String> getAllCategories() {
		return clothRepository.findAllCategories();
	}

	@Override
	@Cacheable("brands")
	public List<String> getAllBrands() {
		return clothRepository.findAllBrands();
	}
}
