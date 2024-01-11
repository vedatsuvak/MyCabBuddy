package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cab;

public interface CabRepo extends JpaRepository<Cab, Integer> {

	String query = "SELECT c FROM Cab c WHERE c.cabType = ?1";

	@Query(query)
	List<Cab> findDrivers(String cabType);

	String query1 = "SELECT c FROM Cab c WHERE c.driverName = ?1";

	@Query(query1)
	Cab findDriverByName(String driverName);

	String queryUpdate = "UPDATE Cab c SET c.cabType = ?2, c.driverName = ?3, c.driverRating = ?4, c.cabCapacity = ?5, c.pricePercentage = ?6 WHERE c.id = ?1";

	@Transactional
	@Modifying
	@Query(queryUpdate)
	int update(int id, String cabType, String driverName, double driverRating, int cabCapacity, double pricePercentage);
}
