package com.spark.lms.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spark.lms.mdl.Member;
import com.spark.lms.service.membserv;

@RestController
@RequestMapping(value = "/rest/member")
public class membrstcntrl {

	@Autowired
	private membserv membservc;
	
	@GetMapping(value = {"/", "/list"})
	public List<Member> al() {
		return membservc.getal();
	}
	
}
