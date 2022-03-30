package com.sakinr.patika.airportreservationsystem.repository;

import com.sakinr.patika.airportreservationsystem.model.entity.AirportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportCompanyRepository extends JpaRepository<AirportCompany, Integer> {

    AirportCompany getByName(String name);
}
