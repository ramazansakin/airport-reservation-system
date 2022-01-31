package com.sakinr.patika.airportreservatinsystem.service.iml;


import com.sakinr.patika.airportreservatinsystem.exception.NotFoundException;
import com.sakinr.patika.airportreservatinsystem.model.Airport;
import com.sakinr.patika.airportreservatinsystem.model.Route;
import com.sakinr.patika.airportreservatinsystem.repository.RouteRepository;
import com.sakinr.patika.airportreservatinsystem.service.AirportService;
import com.sakinr.patika.airportreservatinsystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final AirportService airportService;

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRoute(Integer id) {
        Optional<Route> byId = routeRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Route"));
    }

    @Override
    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public boolean deleteRoute(Integer id) {
        routeRepository.delete(getRoute(id));
        return true;
    }

    @Override
    public Route getFirstRouteByDepartureAirportByDefault(Integer departure_airport_id) {
        Airport dep_airport = airportService.getAirport(departure_airport_id);
        Optional<Route> byDepartureAirport = Optional.ofNullable(routeRepository.findByDepartureAirport(dep_airport));
        return byDepartureAirport.orElseThrow(() -> new NotFoundException("FirstRouteByDepartureAirport"));
    }

}
