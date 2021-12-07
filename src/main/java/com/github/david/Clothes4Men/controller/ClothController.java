package com.github.david.Clothes4Men.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.david.Clothes4Men.domain.Cloth;
import com.github.david.Clothes4Men.domain.ClothBuilder;
import com.github.david.Clothes4Men.domain.Brand;
import com.github.david.Clothes4Men.domain.Category;
import com.github.david.Clothes4Men.domain.Size;
import com.github.david.Clothes4Men.service.ClothService;

@Controller
@RequestMapping("/cloth")
public class ClothController {

	@Autowired
	private ClothService clothService;
	
	@RequestMapping("/add")
	public String addCloth(Model model) {
		Cloth cloth = new Cloth();
		model.addAttribute("cloth", cloth);
		model.addAttribute("allSizes", clothService.getAllSizes());
		model.addAttribute("allBrands", clothService.getAllBrands());
		model.addAttribute("allCategories", clothService.getAllCategories());
		return "addCloth";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addClothPost(@ModelAttribute("cloth") Cloth cloth, HttpServletRequest request) {
		Cloth newCloth = new ClothBuilder()
				.withTitle(cloth.getTitle())
				.stockAvailable(cloth.getStock())
				.withPrice(cloth.getPrice())
				.imageLink(cloth.getPicture())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
				.build();		
		clothService.saveCloth(newCloth);	
		return "redirect:cloth-list";
	}
	
	@RequestMapping("/cloth-list")
	public String clothList(Model model) {
		List<Cloth> cloths = clothService.findAllCloths();
		model.addAttribute("cloths", cloths);
		return "clothList";
	}
	
	@RequestMapping("/edit")
	public String editCloth(@RequestParam("id") Long id, Model model) {
		Cloth cloth = clothService.findClothById(id);
		String preselectedSizes = "";
		for (Size size : cloth.getSizes()) {
			preselectedSizes += (size.getValue() + ",");
		}
		String preselectedBrands = "";
		for (Brand brand : cloth.getBrands()) {
			preselectedBrands += (brand.getName() + ",");
		}
		String preselectedCategories = "";
		for (Category category : cloth.getCategories()) {
			preselectedCategories += (category.getName() + ",");
		}		
		model.addAttribute("cloth", cloth);
		model.addAttribute("preselectedSizes", preselectedSizes);
		model.addAttribute("preselectedBrands", preselectedBrands);
		model.addAttribute("preselectedCategories", preselectedCategories);
		model.addAttribute("allSizes", clothService.getAllSizes());
		model.addAttribute("allBrands", clothService.getAllBrands());
		model.addAttribute("allCategories", clothService.getAllCategories());
		return "editCloth";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editClothPost(@ModelAttribute("cloth") Cloth cloth, HttpServletRequest request) {		
		Cloth newCloth = new ClothBuilder()
				.withTitle(cloth.getTitle())
				.stockAvailable(cloth.getStock())
				.withPrice(cloth.getPrice())
				.imageLink(cloth.getPicture())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
				.build();
		newCloth.setId(cloth.getId());
		clothService.saveCloth(newCloth);	
		return "redirect:cloth-list";
	}
	
	@RequestMapping("/delete")
	public String deleteCloth(@RequestParam("id") Long id) {
		clothService.deleteClothById(id);
		return "redirect:cloth-list";
	}
	
}
