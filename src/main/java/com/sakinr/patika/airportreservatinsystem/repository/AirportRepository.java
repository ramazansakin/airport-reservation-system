package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
