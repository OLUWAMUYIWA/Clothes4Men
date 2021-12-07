package com.github.david.Clothes4Men.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.david.Clothes4Men.domain.Cloth;
import com.github.david.Clothes4Men.service.ClothService;

@Controller
public class HomeController {
		
	@Autowired
	private ClothService clothService;
	
	
	@RequestMapping("/")
	public String index(Model model) {		
		List<Cloth> cloths = clothService.findFirstCloths();
		model.addAttribute("cloths", cloths);
		return "index";
	}

	
}
