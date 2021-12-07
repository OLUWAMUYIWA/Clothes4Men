package com.github.david.Clothes4Men.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import com.github.david.Clothes4Men.domain.Order;
import com.github.david.Clothes4Men.domain.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByUser(User user); 
	
	@EntityGraph(attributePaths = { "cartItems", "payment", "shipping" })
	Order findEagerById(Long id);

}
