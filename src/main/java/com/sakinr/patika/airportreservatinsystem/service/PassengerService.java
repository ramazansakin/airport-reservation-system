package com.sakinr.patika.airportreservatinsystem.service;

import com.sakinr.patika.airportreservatinsystem.model.entity.Passenger;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PassengerService {
    List<Passenger> getAllPassengers();

    Passenger getPassenger(Integer id);

    void addPassenger(@RequestBody Passenger passenger);

    Passenger updatePassenger(@RequestBody Passenger passenger);

    boolean deletePassenger(Integer id);

    List<Passenger> getPassengersNameStartsWith(String prefix);

    List<Passenger> getPassengersSortedViaLastNameAsUpperCase();

}
