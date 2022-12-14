package com.spark.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spark.lms.service.homserv;

@Controller
public class homecntr {

	@Autowired
	homserv homservc;
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String hompag(Model mdl) {
		mdl.addAttribute("topTiles", homservc.getTopTilesMap());
		return "home";
	}	
	
}
