package com.sakinr.patika.airportreservatinsystem.service.iml;

import com.sakinr.patika.airportreservatinsystem.model.Airport;
import com.sakinr.patika.airportreservatinsystem.repository.AirportRepository;
import com.sakinr.patika.airportreservatinsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        List<Airport> all = airportRepository.findAll();
        return all;
    }

    @Override
    public Airport getAirport(Integer id) {
        Optional<Airport> byId = airportRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public boolean addAirport(Airport airport) {
        Airport save = airportRepository.save(airport);
        if (save == null)
            return false;
        return true;
    }

    @Override
    public Airport updateAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public boolean deleteAirport(Integer id) {
        airportRepository.deleteById(id);
        return true;
    }
}
