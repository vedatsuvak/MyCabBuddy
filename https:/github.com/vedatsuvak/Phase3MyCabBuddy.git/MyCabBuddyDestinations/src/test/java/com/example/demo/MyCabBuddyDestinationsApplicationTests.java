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

import com.example.demo.model.Destination;
import com.example.demo.service.DestinationService;

@SpringBootTest(classes = { MyCabBuddyDestinationsApplication.class, TestConfig.class })
class MyCabBuddyDestinationsApplicationTests {

	@Autowired
	private DestinationService destinationService;

	private Destination testDestination;

	@BeforeEach
	void setUp() {
		testDestination = new Destination();
		testDestination.setSource("SourceCity");
		testDestination.setDestination("DestinationCity");
		testDestination.setPrice(50);
		testDestination = destinationService.insert(testDestination);
	}

	@AfterEach
	void tearDown() {
		if (testDestination != null) {
			destinationService.deleteById(testDestination.getId());
		}
	}

	@Test
	void testInsertDestination() {
		assertNotNull(testDestination);
		assertEquals("SourceCity", testDestination.getSource());
		assertEquals("DestinationCity", testDestination.getDestination());
		assertEquals(50, testDestination.getPrice());
	}

	@Test
	void testGetAllDestinations() {
		List<Destination> destinations = destinationService.getAll();
		assertNotNull(destinations);
		assertFalse(destinations.isEmpty());
	}

	@Test
	void testFindDestinationById() {
		Optional<Destination> foundDestination = destinationService.findById(testDestination.getId());

		assertTrue(foundDestination.isPresent());
		assertEquals(testDestination.getId(), foundDestination.get().getId());
		assertEquals("SourceCity", foundDestination.get().getSource());
		assertEquals("DestinationCity", foundDestination.get().getDestination());
		assertEquals(50, foundDestination.get().getPrice());
	}

	@Test
	void testFindPrice() {
		Integer foundPrice = destinationService.findprice("SourceCity", "DestinationCity");
		assertNotNull(foundPrice);
		assertEquals(50, foundPrice);
	}

	@Test
	void testFindDestination() {
		Destination foundDestination = destinationService.findDestination("SourceCity", "DestinationCity");
		assertNotNull(foundDestination);
		assertEquals(testDestination.getId(), foundDestination.getId());
		assertEquals("SourceCity", foundDestination.getSource());
		assertEquals("DestinationCity", foundDestination.getDestination());
		assertEquals(50, foundDestination.getPrice());
	}

	@Test
	void testUpdateDestination() {
		testDestination.setPrice(60);

		Destination updatedDestination = destinationService.updateDestination(testDestination);

		assertNotNull(updatedDestination);
		assertEquals(testDestination.getId(), updatedDestination.getId());
		assertEquals("SourceCity", updatedDestination.getSource());
		assertEquals("DestinationCity", updatedDestination.getDestination());
		assertEquals(60, updatedDestination.getPrice());
	}

	@Test
	void testDeleteDestinationById() {
		String result = destinationService.deleteById(testDestination.getId());
		assertEquals("Deleted", result);
	}
}
