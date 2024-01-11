package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@SpringBootTest(classes = { MyCabBuddyUserApplication.class, TestConfig.class })
class MyCabBuddyUserApplicationTests {

	@Autowired
	private UserService userService;

	private User testUser;

	@BeforeEach
	void setUp() {
		testUser = new User();
		testUser.setUsername("testUser");
		testUser.setFullname("Test User");
		testUser.setEmail("testuser@example.com");
		testUser.setPhone("1234567890");
		testUser.setPassword("password");
		testUser = userService.register(testUser);
	}

	@AfterEach
	void tearDown() {
		if (testUser.getId() > 0) {
			userService.deleteById(testUser.getId());
		}
	}

	@Test
	void testRegisterUser() {
		assertNotNull(testUser);
		assertEquals("testUser", testUser.getUsername());
		assertEquals("Test User", testUser.getFullname());
		assertEquals("testuser@example.com", testUser.getEmail());
		assertEquals("1234567890", testUser.getPhone());
		assertEquals("password", testUser.getPassword());
	}

	@Test
	void testLoginUser() {
		// Login with valid credentials
		User loggedInUser = userService.login("testUser", "password");
		assertNotNull(loggedInUser);
		assertEquals("testUser", loggedInUser.getUsername());
	}

	@Test
	void testGetAllUsers() {
		// Retrieve all users
		List<User> users = userService.getAllUsers();
		assertNotNull(users);
		assertEquals(testUser, users.get(users.size() - 1));
		assertFalse(users.isEmpty());
	}

	@Test
	void testFindUserById() {
		// Find the user by ID
		Optional<User> foundUser = userService.findUser(testUser.getId());
		assertTrue(foundUser.isPresent());
		assertEquals("testUser", foundUser.get().getUsername());
	}

	@Test
	void testFindByEmail() {
		// Find the user by email
		User foundUser = userService.findbyemail("testuser@example.com");
		assertNotNull(foundUser);
		assertEquals("testUser", foundUser.getUsername());
	}

	@Test
	void testUpdateUser() {
		// Update the user's information
		testUser.setPhone("9876543210");
		User updatedUser = userService.updateUser(testUser);
		assertNotNull(updatedUser);
		assertEquals("9876543210", updatedUser.getPhone());
	}

	@Test
	void testDeleteUserById() {
		// Delete the user by ID
		String result = userService.deleteById(testUser.getId());
		assertEquals("Deleted", result);
	}
}
