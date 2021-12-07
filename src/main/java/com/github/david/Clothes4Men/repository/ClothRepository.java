package com.github.david.Clothes4Men.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.github.david.Clothes4Men.domain.Cloth;

public interface ClothRepository extends JpaRepository<Cloth, Long>, JpaSpecificationExecutor<Cloth> {
	
	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	List<Cloth> findAllEagerBy();	
		
	@EntityGraph(attributePaths = { "sizes", "categories", "brands" })
	Optional<Cloth> findById(Long id);
	
	@Query("SELECT DISTINCT s.value FROM Size s")
	List<String> findAllSizes();
	
	@Query("SELECT DISTINCT c.name FROM Category c")
	List<String> findAllCategories();
	
	@Query("SELECT DISTINCT b.name FROM Brand b")
	List<String> findAllBrands();
	
}
