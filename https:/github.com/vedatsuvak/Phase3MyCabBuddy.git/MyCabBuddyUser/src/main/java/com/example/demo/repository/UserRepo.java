package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	String query1 = "select user from User user where user.username=?1 and user.password=?2";

	@Query(query1)
	public User login(String username, String password);
}