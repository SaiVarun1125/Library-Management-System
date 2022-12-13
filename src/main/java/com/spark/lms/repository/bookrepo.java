package com.spark.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.lms.mdl.Book;
import com.spark.lms.mdl.Category;

@Repository
public interface bookrepo extends JpaRepository<Book, Long> {
	public Book findByTag(String tg);
	public List<Book> findByCategory(Category catey);
	public List<Book> findByCategoryAndStatus(Category caty, Integer stat);
	public Long countByStatus(Integer stat);
}
