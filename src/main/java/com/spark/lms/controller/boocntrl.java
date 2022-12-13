package com.spark.lms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spark.lms.mdl.Book;
import com.spark.lms.mdl.Category;
import com.spark.lms.service.bookser;
import com.spark.lms.service.catagoser;

@Controller
@RequestMapping(value = "/book")
public class boocntrl {

	@Autowired
	private bookser booservc;
	
	@Autowired
	private catagoser catgosrvc;
	
	@ModelAttribute(name = "categories")
	public List<Category> categories() {
		return catgosrvc.getalbysor();
	}
	
	@RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
	public String shwboopag(Model mdl) {
		mdl.addAttribute("book", booservc.getal());
		return "/book/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addboopag(Model mdl) {
		mdl.addAttribute("book", new Book());
		return "/book/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String ediboopag(@PathVariable(name = "id") Long id,  Model mdl) {
		Book book = booservc.get(id);
		if( book != null ) {
			mdl.addAttribute("book", book);
			return "/book/form";
		} else {
			return "redirect:/book/add";
		}
	}
	
	@RequestMapping(value = "/sve", method = RequestMethod.POST)
	public String savboo(@Valid Book book, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if( bindingResult.hasErrors() ) {
			return "/book/form";
		}
		
		if( book.getId() == null ) {
			if( booservc.getbtg(book.getTag()) != null ) {
				bindingResult.rejectValue("tag", "tag", "Tag already exists");
				return "/book/form";
			} else {
				booservc.adnw(book);
				redirectAttributes.addFlashAttribute("successMsg", "'" + book.getTitle() + "' is added as a new Book.");
				return "redirect:/book/add";
			}
		} else {
			Book updatedBook = booservc.sve(book);
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + book.getTitle() + "' are saved successfully. ");
			return "redirect:/book/edit/"+updatedBook.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeBook(@PathVariable(name = "id") Long id, Model mdl) {
		Book book = booservc.get( id );
		if( book != null ) {
			if( booservc.hasusg(book) ) {
				mdl.addAttribute("bookInUse", true);
				return shwboopag(mdl);
			} else {
				booservc.del(id);
			}
		}
		return "redirect:/book/list";
	}
}
