package com.spark.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.Book;
import com.spark.lms.mdl.Category;
import com.spark.lms.repository.bookrepo;

@Service
public class bookser {

	@Autowired
	private bookrepo bookrepos;
	
	@Autowired
	private issuebook issbookserv;
	
	public Long gettcnt() {
		return bookrepos.count();
	}
	
	public Long gettoisbook() {
		return bookrepos.countByStatus(coonst.boostatiss);
	}
	
	public List<Book> getal() {
		return bookrepos.findAll();
	}
	
	public Book get(Long id) {
		return bookrepos.findById(id).get();
	}
	
	public Book getbtg(String tag) {
		return bookrepos.findByTag(tag);
	}
	
	public List<Book> get(List<Long> ids) {
		return bookrepos.findAllById(ids);
	}
	
	public List<Book> getbycag(Category category) {
		return bookrepos.findByCategory(category);
	}
	
	public List<Book> getabbycag(Category category) {
		return bookrepos.findByCategoryAndStatus(category, coonst.boostatavil);
	}
	
	public Book adnw(Book book) {
		book.setCreateDate(new Date());
		book.setStatus( coonst.boostatavil );
		return bookrepos.save(book);
	}
	
	public Book sve(Book book) {
		return bookrepos.save(book);
	}
	
	public void del(Book book) {
		bookrepos.delete(book);
	}
	
	public void del(Long id) {
		bookrepos.deleteById(id);
	}
	
	public boolean hasusg(Book book) {
		return issbookserv.getCountByBook(book)>0;
	}
}
