package com.sakinr.patika.airportreservatinsystem.service;

import com.sakinr.patika.airportreservatinsystem.model.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> getAllAirports();

    Airport getAirport(Integer id);

    void addAirport(Airport airport);

    Airport updateAirport(Integer id, Airport airport);

    boolean deleteAirport(Integer id);
}
