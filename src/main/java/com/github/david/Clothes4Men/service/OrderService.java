package com.github.david.Clothes4Men.service;

import java.util.List;

import com.github.david.Clothes4Men.domain.Order;
import com.github.david.Clothes4Men.domain.Payment;
import com.github.david.Clothes4Men.domain.Shipping;
import com.github.david.Clothes4Men.domain.ShoppingCart;
import com.github.david.Clothes4Men.domain.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
