package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
//spring boot app is open to gt accessed for any external applciation 
//@CrossOrigin(origins = "localhost:4200")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService service;

	// Done
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		List<User> users = service.getAllUsers();
		for (User singleUser : users) {
			if (user.getUsername() != null && user.getUsername().equals(singleUser.getUsername())) {
				return "Hey " + user.getUsername() + ", already registered, please choose another username!!!";
			} else if (user.getEmail() != null && user.getEmail().equals(singleUser.getEmail())) {
				return "Hey " + user.getEmail() + ", already registered, please choose another email!!!";
			}
		}
		service.register(user);
		return "Hey " + user.getUsername() + ", you are registered successfully! Go to Login to enter system!";
	}

	// Done
	@PostMapping("/login/{username}/{password}")
	public User login(@PathVariable String username, @PathVariable String password) {
		User user = service.login(username, password);
		if (user == null) {
			return null;
		} else {
			return user;
		}
	}

	// Done
	@GetMapping("/getUser/{id}")
	public User findUser(@PathVariable int id) {
		System.out.println(id);
		return service.findUser(id).orElse(null);
	}

	// Done
	@PostMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		System.out.println(user);
		return service.updateUser(user);
	}

	// Done
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		service.deleteById(id);
		return "User deleted successfully";
	}

	// Done
	@GetMapping("/getAllusers")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	// Not necessary
	@GetMapping("/findbyemail/{email}")
	public User findyemail(@PathVariable String email) {
		return service.findbyemail(email);
	}

}