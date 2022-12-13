package com.spark.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.lms.mdl.User;

@Repository
public interface userepo extends JpaRepository<User, Long> {
	public List<User> findAllByOrderByDisplayNameAsc();
	public List<User> findAllByActiveOrderByDisplayNameAsc(Integer act);
	public User findByUsername(String usr);
}
