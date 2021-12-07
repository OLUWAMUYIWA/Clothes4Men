package com.github.david.Clothes4Men.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.david.Clothes4Men.domain.Cloth;
import com.github.david.Clothes4Men.domain.CartItem;
import com.github.david.Clothes4Men.domain.ShoppingCart;
import com.github.david.Clothes4Men.domain.User;
import com.github.david.Clothes4Men.service.ClothService;
import com.github.david.Clothes4Men.service.ShoppingCartService;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
		
	@Autowired
	private ClothService clothService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Authentication authentication) {		
		User user = (User) authentication.getPrincipal();		
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);		
		model.addAttribute("cartItemList", shoppingCart.getCartItems());
		model.addAttribute("shoppingCart", shoppingCart);		
		return "shoppingCart";
	}

	@RequestMapping("/add-item")
	public String addItem(@ModelAttribute("cloth") Cloth cloth, @RequestParam("qty") String qty,
						  @RequestParam("size") String size, RedirectAttributes attributes, Model model, Authentication authentication) {
		cloth = clothService.findClothById(cloth.getId());				
		if (!cloth.hasStock(Integer.parseInt(qty))) {
			attributes.addFlashAttribute("notEnoughStock", true);
			return "redirect:/cloth-detail?id="+cloth.getId();
		}		
		User user = (User) authentication.getPrincipal();		
		shoppingCartService.addClothToShoppingCart(cloth, user, Integer.parseInt(qty), size);
		attributes.addFlashAttribute("addClothSuccess", true);
		return "redirect:/cloth-detail?id="+cloth.getId();
	}
	
	@RequestMapping("/update-item")
	public String updateItemQuantity(@RequestParam("id") Long cartItemId,
									 @RequestParam("qty") Integer qty, Model model) {		
		CartItem cartItem = shoppingCartService.findCartItemById(cartItemId);
		if (cartItem.canUpdateQty(qty)) {
			shoppingCartService.updateCartItem(cartItem, qty);
		}
		return "redirect:/shopping-cart/cart";
	}
	
	@RequestMapping("/remove-item")
	public String removeItem(@RequestParam("id") Long id) {		
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));		
		return "redirect:/shopping-cart/cart";
	} 
}
