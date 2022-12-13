package com.spark.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.lms.mdl.Book;
import com.spark.lms.mdl.IssuedBook;

@Repository
public interface issbooreo extends JpaRepository<IssuedBook, Long> {
	public Long countByBookAndReturned(Book boo, Integer ret);
}
