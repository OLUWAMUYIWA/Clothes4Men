package com.github.david.Clothes4Men.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.github.david.Clothes4Men.domain.CartItem;
import com.github.david.Clothes4Men.domain.User;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	@EntityGraph(attributePaths = { "cloth" })
	List<CartItem> findAllByUserAndOrderIsNull(User user);
	
	void deleteAllByUserAndOrderIsNull(User user);

	int countDistinctByUserAndOrderIsNull(User user);
}
