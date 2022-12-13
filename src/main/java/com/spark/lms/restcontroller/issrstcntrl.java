package com.spark.lms.restcontroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.Book;
import com.spark.lms.mdl.Issue;
import com.spark.lms.mdl.IssuedBook;
import com.spark.lms.mdl.Member;
import com.spark.lms.service.bookser;
import com.spark.lms.service.issserv;
import com.spark.lms.service.issuebook;
import com.spark.lms.service.membserv;

@RestController
@RequestMapping(value = "/rest/issue")
public class issrstcntrl {

	@Autowired
	private membserv memservc;
	
	@Autowired
	private bookser booservc;
	
	@Autowired
	private issserv issservc;
	
	@Autowired
	private issuebook issbooservc;
	
	@RequestMapping(value="/sve", method = RequestMethod.POST)
	public String sve(@RequestParam Map<String, String> payload) {
		
		String memberIdStr = (String)payload.get("member");
		String[] bookIdsStr = payload.get("books").toString().split(",");
		
		Long memberId = null;
		List<Long> bookIds = new ArrayList<Long>();
		try {
			memberId = Long.parseLong( memberIdStr );
			for(int k=0 ; k<bookIdsStr.length ; k++) {
				bookIds.add( Long.parseLong(bookIdsStr[k]) );
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "invalid number format";
		}
		
		Member member = memservc.get( memberId );
		List<Book> books = booservc.get(bookIds);
		
		Issue issue = new Issue();
		issue.setMember(member);
		issue = issservc.adnw(issue);
		
		for( int k=0 ; k<books.size() ; k++ ) {
			Book book = books.get(k);
			book.setStatus( coonst.boostatiss );
			book = booservc.sve(book);
			
			IssuedBook ib = new IssuedBook();
			ib.setBook( book );
			ib.setIssue( issue );
			issbooservc.adnw( ib );
			
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
	public String retall(@PathVariable(name = "id") Long id) {
		Issue issue = issservc.get(id);
		if( issue != null ) {
			List<IssuedBook> issuedBooks = issue.getIssuedBooks();
			for( int k=0 ; k<issuedBooks.size() ; k++ ) {
				IssuedBook ib = issuedBooks.get(k);
				ib.setReturned( coonst.booretu );
				issbooservc.sve( ib );
				
				Book book = ib.getBook();
				book.setStatus( coonst.boostatavil );
				booservc.sve(book);
			}
			
			issue.setReturned( coonst.booretu );
			issservc.sve(issue);
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
	@RequestMapping(value="/{id}/return", method = RequestMethod.POST)
	public String retsel(@RequestParam Map<String, String> payload, @PathVariable(name = "id") Long id) {
		Issue issue = issservc.get(id);
		String[] issuedBookIds = payload.get("ids").split(",");
		if( issue != null ) {
			
			List<IssuedBook> issuedBooks = issue.getIssuedBooks();
			for( int k=0 ; k<issuedBooks.size() ; k++ ) {
				IssuedBook ib = issuedBooks.get(k);
				if( Arrays.asList(issuedBookIds).contains( ib.getId().toString() ) ) {
					ib.setReturned( coonst.booretu );
					issbooservc.sve( ib );
					
					Book book = ib.getBook();
					book.setStatus( coonst.boostatavil );
					booservc.sve(book);
				}
			}
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
}
