package com.spark.lms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spark.lms.mdl.Category;
import com.spark.lms.service.catagoser;

@Controller
@RequestMapping(value = "/category")
public class catagcntrl {

	@Autowired
	private catagoser catgsrvc;
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String shwcatpag(Model mdl) {
		mdl.addAttribute("categories", catgsrvc.getal());
		return "/category/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addcatpag(Model mdl) {
		mdl.addAttribute("category", new Category());
		return "/category/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edicatpag(@PathVariable(name = "id") Long id, Model mdl) {
		Category category = catgsrvc.get(id);
		if( category != null ) {
			mdl.addAttribute("category", category);
			return "/category/form";
		} else {
			return "redirect:/category/add";
		}
	}
	
	@RequestMapping(value = "/sve", method = RequestMethod.POST)
	public String savcat(@Valid Category category, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if( bindingResult.hasErrors() ) {
			return "/category/form";
		}
		
		if( category.getId() == null ) {
			catgsrvc.adnw(category);
			redirectAttributes.addFlashAttribute("successMsg", "'" + category.getName() + "' is added as a new category.");
			return "redirect:/category/add";
		} else {
			Category updateCategory = catgsrvc.sve( category );
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + category.getName() + "' are saved successfully. ");
			return "redirect:/category/edit/"+updateCategory.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remcat(@PathVariable(name = "id") Long id, Model mdl) {
		Category category = catgsrvc.get( id );
		if( category != null ) {
			if( catgsrvc.hasusg(category) ) {
				mdl.addAttribute("categoryInUse", true);
				return shwcatpag(mdl);
			} else {
				catgsrvc.del(id);
			}
		}
		return "redirect:/category/list";
	}
	
}
