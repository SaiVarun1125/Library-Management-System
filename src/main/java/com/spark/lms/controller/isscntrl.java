package com.spark.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.Category;
import com.spark.lms.service.catagoser;
import com.spark.lms.service.issserv;

@Controller
@RequestMapping(value = "/issue")
public class isscntrl {

	@Autowired
	private issserv issservc;
	
	@Autowired
	private catagoser categoryService;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> memtyp() {
		return coonst.memtyp;
	}
	
	@ModelAttribute("categories")
	public List<Category> getcatg() {
		return categoryService.getalbysor();
	}
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String lstisspag(Model mdl) {
		mdl.addAttribute("issues", issservc.getalunret());
		return "/issue/list";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newisspag(Model mdl) { 
		return "/issue/form";
	}
	
}
