package com.sakinr.patika.airportreservatinsystem.service.iml;

import com.sakinr.patika.airportreservatinsystem.model.Airport;
import com.sakinr.patika.airportreservatinsystem.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AirportServiceImpl implements AirportService {

    private List<Airport> airports = new ArrayList<>();

    {
        airports.add(new Airport("Airport 1"));
        airports.add(new Airport("Airport 2"));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airports;
    }

    @Override
    public Airport getAirport(Integer id) {
        return null;
    }

    @Override
    public boolean addAirport(Airport airport) {
        return airports.add(airport);
    }

    @Override
    public Airport updateAirport(String name, Airport airport) {
        AtomicBoolean status = new AtomicBoolean(false);

//        for (Airport airp : this.airports) {
//            if (airp.getName().equals(name)) {
//                airp.setName(airport.getName());
//                return airp;
//            }
//        }
//        return null;
//
        airports.forEach((airportItem) -> {
            if (airportItem.getName().equals(name)) {
                status.set(true);
                airportItem.setName(airport.getName());
            }
        });
        if (status.get())
            return airport;
        return null;
    }

    @Override
    public boolean deleteAirport(Integer id) {
        return false;
    }
}
