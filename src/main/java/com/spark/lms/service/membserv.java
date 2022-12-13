package com.spark.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.Member;
import com.spark.lms.repository.membrepo;

@Service
public class membserv {

	@Autowired
	private membrepo memberRepository;
	
	@Autowired
	private issserv issueService;
	
	public Long gettcnt() {
		return memberRepository.count();
	}
	
	public Long getParentsCount() {
		return memberRepository.countByType(coonst.membpar);
	}
	
	public Long getStudentsCount() {
		return memberRepository.countByType(coonst.membstd);
	}
	
	public List<Member> getal() {
		return memberRepository. findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
	}
	
	public Member get(Long id) {
		return memberRepository.findById(id).get();
	}
	
	public Member adnw(Member member) {
		member.setJoiningDate( new Date() );
		return memberRepository.save( member );
	}
	
	public Member sve(Member member) {
		return memberRepository.save( member );
	}
	
	public void del(Member member) {
		memberRepository.delete(member);
	}
	
	public void del(Long id) {
		memberRepository.deleteById(id);
	}
	
	public boolean hasusg(Member member) {
		return issueService.getcntmemb(member) > 0;
	}
	
}