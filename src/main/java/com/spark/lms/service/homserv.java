package com.spark.lms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.common.coonst;

@Service
public class homserv {

	@Autowired
	private membserv memservc;
	
	@Autowired
	private catagoser cateserv;
	
	@Autowired
	private bookser bokservc;
	
	public Map<String, Long> getTopTilesMap() {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("totalMembers", memservc.gettcnt());
		map.put("totalStudents", memservc.getStudentsCount());
		map.put("totalParents", memservc.getParentsCount());
		map.put("totalCategories", cateserv.gettcnt());
		map.put("totalBooks", bokservc.gettcnt());
		map.put("totalIssuedBooks", bokservc.gettoisbook());
		return map;
	}
	
}
