package com.github.david.Clothes4Men.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.david.Clothes4Men.domain.Cloth;
import com.github.david.Clothes4Men.form.ClothFilterForm;
import com.github.david.Clothes4Men.service.ClothService;
import com.github.david.Clothes4Men.type.SortFilter;

@Controller
public class StoreController {
	
	@Autowired
	private ClothService clothService;
	
	@RequestMapping("/store")
	public String store(@ModelAttribute("filters") ClothFilterForm filters, Model model) {
		Integer page = filters.getPage();			
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		SortFilter sortFilter = new SortFilter(filters.getSort());	
		Page<Cloth> pageresult = clothService.findClothsByCriteria(PageRequest.of(pagenumber,9, sortFilter.getSortType()), 
																filters.getPricelow(), filters.getPricehigh(), 
																filters.getSize(), filters.getCategory(), filters.getBrand(), filters.getSearch());	
		model.addAttribute("allCategories", clothService.getAllCategories());
		model.addAttribute("allBrands", clothService.getAllBrands());
		model.addAttribute("allSizes", clothService.getAllSizes());
		model.addAttribute("cloths", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 9);
		return "store";
	}
	
	
	@RequestMapping("/cloth-detail")
	public String clothDetail(@PathParam("id") Long id, Model model) {
		Cloth cloth = clothService.findClothById(id);
		model.addAttribute("cloth", cloth);
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addClothSuccess", model.asMap().get("addClothSuccess"));
		return "clothDetail";
	}
	

}
