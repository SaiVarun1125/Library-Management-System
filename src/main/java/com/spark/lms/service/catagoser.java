package com.spark.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.mdl.Category;
import com.spark.lms.repository.catgrepo;

@Service
public class catagoser {

	@Autowired
	private catgrepo catagrepos;
	
	public Long gettcnt() {
		return catagrepos.count();
	}
	
	public List<Category> getalbysor() {
		return catagrepos.findAllByOrderByNameAsc();
	}
	
	public List<Category> getal() {
		return catagrepos.findAll();
	}
	
	public Category get(Long id) {
		return catagrepos.findById(id).get();
	}
	
	public Category adnw(Category category) {
		category.setCreateDate(new Date());
		return catagrepos.save(category);
	}
	
	public Category sve(Category category) {
		return catagrepos.save(category);
	}
	
	public void del(Category category) {
		catagrepos.delete(category);
	}
	
	public void del(Long id) {
		catagrepos.deleteById(id);
	}
	
	public boolean hasusg(Category category) {
		return category.getBooks().size()>0;
	}
}
