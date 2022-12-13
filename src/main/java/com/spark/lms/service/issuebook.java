package com.spark.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.Book;
import com.spark.lms.mdl.IssuedBook;
import com.spark.lms.repository.issbooreo;

@Service
public class issuebook {

	@Autowired
	private issbooreo issuedBookR;
	
	public List<IssuedBook> getal() {
		return issuedBookR.findAll();
	}
	
	public IssuedBook get(Long id) {
		return issuedBookR.findById(id).get();
	}
	
	public Long getCountByBook(Book book) {
		return issuedBookR.countByBookAndReturned(book, coonst.boonotret);
	}
	
	public IssuedBook sve(IssuedBook issuedBook) {
		return issuedBookR.save(issuedBook);
	}
	
	public IssuedBook adnw(IssuedBook issuedBook) {
		issuedBook.setReturned( coonst.boonotret );
		return issuedBookR.save(issuedBook);
	}

}
