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

import com.example.demo.model.Cab;
import com.example.demo.service.CabService;

@SpringBootTest(classes = { MyCabBuddyCabApplication.class, TestConfig.class })
class MyCabBuddyCabApplicationTests {

	@Autowired
	private CabService cabService;

	private Cab testCab;

	@BeforeEach
	void setUp() {
		testCab = new Cab();
		testCab.setCabType("Sedan");
		testCab.setDriverName("John Doe");
		testCab.setDriverRating(4.5);
		testCab.setCabCapacity(4);
		testCab.setPricePercentage(10.0);
		testCab = cabService.save(testCab);
	}

	@AfterEach
	void tearDown() {
		if (testCab != null) {
			cabService.deleteById(testCab.getId());
		}
	}

	// Test saving a cab
	@Test
	void testSaveCab() {
		assertNotNull(testCab);
		assertEquals(testCab, cabService.save(testCab));
	}

	// Test finding all cabs
	@Test
	void testFindAllCabs() {
		List<Cab> cabList = cabService.findAll();
		assertNotNull(cabList);
		assertFalse(cabList.isEmpty());
		assertEquals(11, cabList.size());
	}

	// Test finding drivers by type
	@Test
	void testFindDriversByType() {
		String cabType = "Sedan";
		List<Cab> cabList = cabService.findDrivers(cabType);
		assertNotNull(cabList);
		assertFalse(cabList.isEmpty());
		assertEquals(1, cabList.size());
	}

	// Test finding a driver by name
	@Test
	void testFindDriverByName() {
		String driverName = "John Doe";
		Cab foundCab = cabService.findDriverByName(driverName);
		assertNotNull(foundCab);
		assertEquals(testCab.getCabType(), foundCab.getCabType());
	}

	// Test finding a cab by ID
	@Test
	void testFindCabById() {
		int cabId = testCab.getId();
		Optional<Cab> foundCab = cabService.findById(cabId);
		assertTrue(foundCab.isPresent());
		assertEquals(testCab.getCabType(), foundCab.get().getCabType());
	}

	// Test updating a cab
	@Test
	void testUpdateCab() {
		int updatedCab = cabService.updateCab(testCab);
		assertTrue(updatedCab > 0);
	}

	// Test deleting a cab by ID
	@Test
	void testDeleteCabById() {
		String result = cabService.deleteById(testCab.getId());
		assertEquals("Deleted", result);
	}
}
