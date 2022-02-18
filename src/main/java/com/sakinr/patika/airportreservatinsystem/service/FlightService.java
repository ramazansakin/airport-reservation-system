package com.sakinr.patika.airportreservatinsystem.service;


import com.sakinr.patika.airportreservatinsystem.model.entity.Flight;

import java.util.Date;
import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlight(String id);

    void addFlight(Flight flight);

    Flight updateFlight(Flight flight);

    boolean deleteFlight(String id);

    List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end);

    Flight getFlightByCode(String code);

}
