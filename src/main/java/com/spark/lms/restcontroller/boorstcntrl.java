package com.spark.lms.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spark.lms.mdl.Book;
import com.spark.lms.mdl.Category;
import com.spark.lms.service.bookser;
import com.spark.lms.service.catagoser;

@RestController
@RequestMapping(value = "/rest/book")
public class boorstcntrl {

	@Autowired
	private bookser booservc;
	
	@Autowired
	private catagoser catservc;
	
	@GetMapping(value = {"/", "/list"})
	public List<Book> al() {
		return booservc.getal();
	}
	
	@GetMapping(value = "/{id}/list")
	public List<Book> ge(@PathVariable(name = "id") Long id) {
		Category category = catservc.get(id);
		return booservc.getbycag( category );
	}
	
	@GetMapping(value = "/{id}/available")
	public List<Book> getavailboo(@PathVariable(name = "id") Long id) {
		Category category = catservc.get(id);
		return booservc.getabbycag( category );
	}
	
}
