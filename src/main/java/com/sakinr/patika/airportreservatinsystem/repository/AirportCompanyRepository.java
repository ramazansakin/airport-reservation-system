package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.AirportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportCompanyRepository extends JpaRepository<AirportCompany, Integer> {

    AirportCompany getByName(String name);
}
