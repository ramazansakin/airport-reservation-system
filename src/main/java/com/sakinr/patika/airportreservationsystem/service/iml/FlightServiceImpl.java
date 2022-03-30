package com.sakinr.patika.airportreservationsystem.service.iml;


import com.sakinr.patika.airportreservationsystem.exception.NotFoundException;
import com.sakinr.patika.airportreservationsystem.model.entity.Flight;
import com.sakinr.patika.airportreservationsystem.repository.FlightRepository;
import com.sakinr.patika.airportreservationsystem.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlight(Integer id) {
        Optional<Flight> byId = flightRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Flight"));
    }

    @Override
    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public boolean deleteFlight(Integer id) {
        flightRepository.delete(getFlight(id));
        return true;
    }

    @Override
    public List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end) {
        return flightRepository.getAllByDepartureDateBetween(start, end);
    }

    @Override
    public Flight getFlightByCode(String code) {
        return flightRepository.getByCode(code);
    }


}
