package com.sakinr.patika.airportreservationsystem.service;


import com.sakinr.patika.airportreservationsystem.model.entity.Route;

import java.util.List;

public interface RouteService {

    List<Route> getAllRoutes();

    Route getRoute(Integer id);

    void addRoute(Route route);

    Route updateRoute(Route route);

    boolean deleteRoute(Integer id);

    Route getFirstRouteByDepartureAirportByDefault(Integer departureAirportId);

}
