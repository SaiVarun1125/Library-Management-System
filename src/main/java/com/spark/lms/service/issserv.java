package com.spark.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.Issue;
import com.spark.lms.mdl.Member;
import com.spark.lms.repository.issrepo;

@Service
public class issserv {

	@Autowired
	private issrepo issrepos;
	
	public List<Issue> getal() {
		return issrepos.findAll();
	}
	
	public Issue get(Long id) {
		return issrepos.findById(id).get();
	}
	
	public List<Issue> getalunret() {
		return issrepos.findByReturned( coonst.boonotret );
	}
	
	public Issue adnw(Issue issue) {
		issue.setIssueDate( new Date() );
		issue.setReturned( coonst.boonotret );
		return issrepos.save(issue);
	}
	
	public Issue sve(Issue issue) {
		return issrepos.save(issue);
	}
	
	public Long getcntmemb(Member member) {
		return issrepos.countByMemberAndReturned(member, coonst.boonotret);
	}
}
