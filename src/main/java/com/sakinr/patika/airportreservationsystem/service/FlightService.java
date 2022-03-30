package com.sakinr.patika.airportreservationsystem.service;


import com.sakinr.patika.airportreservationsystem.model.entity.Flight;

import java.util.Date;
import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlight(Integer id);

    void addFlight(Flight flight);

    Flight updateFlight(Flight flight);

    boolean deleteFlight(Integer id);

    List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end);

    Flight getFlightByCode(String code);

}
