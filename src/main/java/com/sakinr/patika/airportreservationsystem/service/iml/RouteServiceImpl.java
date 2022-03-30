package com.sakinr.patika.airportreservationsystem.service.iml;


import com.sakinr.patika.airportreservationsystem.exception.NotFoundException;
import com.sakinr.patika.airportreservationsystem.model.entity.Airport;
import com.sakinr.patika.airportreservationsystem.model.entity.Route;
import com.sakinr.patika.airportreservationsystem.repository.RouteRepository;
import com.sakinr.patika.airportreservationsystem.service.AirportService;
import com.sakinr.patika.airportreservationsystem.service.RouteService;
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
    public Route getFirstRouteByDepartureAirportByDefault(Integer departureAirportId) {
        Airport depAirport = airportService.getAirport(departureAirportId);
        Optional<Route> byDepartureAirport = Optional.ofNullable(routeRepository.findByDepartureAirport(depAirport));
        return byDepartureAirport.orElseThrow(() -> new NotFoundException("FirstRouteByDepartureAirport"));
    }

}
