package com.spark.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spark.lms.mdl.User;
import com.spark.lms.repository.userepo;

@Service
public class Userserv {

	@Autowired
	private userepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers() {
		return userRepo.findAllByOrderByDisplayNameAsc();
	}
	
	
	public List<User> getAllActiveUsers() { return
		userRepo.findAllByActiveOrderByDisplayNameAsc(1);
	}
	
	public User getByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public User getById(Long id) {
		return userRepo.findById(id).get();
	}
	
	public User adnw(User user) {
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		user.setCreatedDate( new Date() );
		user.setLastModifiedDate( user.getCreatedDate() );
		user.setActive(1);
		return userRepo.save(user);
	}
	
	public User update(User user) {
		user.setLastModifiedDate( new Date() );
		return userRepo.save( user );
	}
	
	public void del(User user) {
		userRepo.delete(user);
	}
	
	public void del(Long id) {
		userRepo.deleteById(id);
	}
}
