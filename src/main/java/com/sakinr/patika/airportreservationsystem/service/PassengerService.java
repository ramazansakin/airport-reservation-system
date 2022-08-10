package com.sakinr.patika.airportreservationsystem.service;

import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> getAllPassengers();

    Passenger getPassenger(Integer id);

    void addPassenger(Passenger passenger);

    Passenger updatePassenger(final Integer id, final Passenger passenger);

    boolean deletePassenger(Integer id);

    List<Passenger> getPassengersNameStartsWith(String prefix);

    List<Passenger> getPassengersSortedViaLastNameAsUpperCase();

}
