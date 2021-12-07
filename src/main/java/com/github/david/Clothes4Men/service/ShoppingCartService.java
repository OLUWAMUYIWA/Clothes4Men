package com.github.david.Clothes4Men.service;

import com.github.david.Clothes4Men.domain.Cloth;
import com.github.david.Clothes4Men.domain.CartItem;
import com.github.david.Clothes4Men.domain.ShoppingCart;
import com.github.david.Clothes4Men.domain.User;


public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addClothToShoppingCart(Cloth cloth, User user, int qty, String size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
	
}
