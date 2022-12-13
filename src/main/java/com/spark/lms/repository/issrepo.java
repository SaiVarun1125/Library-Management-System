package com.spark.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.lms.mdl.Issue;
import com.spark.lms.mdl.Member;

@Repository
public interface issrepo extends JpaRepository<Issue, Long> {
	public List<Issue> findByReturned(Integer ret);
	public Long countByMemberAndReturned(Member memb, Integer ret);
}
