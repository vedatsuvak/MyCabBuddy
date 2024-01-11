package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;

	// Insert an User
	public User register(User user) {
		return repo.save(user);
	}

	// Login
	public User login(String username, String password) {
		return repo.login(username, password);
	}

	// Retrieve all Users
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	// Retrieve an User by ID
	public Optional<User> findUser(int id) {
		return repo.findById(id);
	}

	// find a User by email
	public User findbyemail(String email) {
		return repo.findByEmail(email);
	}

	// update User
	public User updateUser(User user) {
		return repo.save(user);
	}

	// Delete an User by ID
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Deleted";
	}

}
