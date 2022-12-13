package com.spark.lms.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spark.lms.mdl.Category;
import com.spark.lms.service.catagoser;

@RestController
@RequestMapping(value = "/rest/category")
public class catrstcntrl {
	
	@Autowired
	private catagoser catsrvc;
	
	@GetMapping(value = {"/", "/list"})
	public List<Category> al() {
		return catsrvc.getal();
	}

}
