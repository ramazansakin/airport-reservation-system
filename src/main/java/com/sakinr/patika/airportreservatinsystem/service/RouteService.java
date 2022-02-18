package com.sakinr.patika.airportreservatinsystem.service;


import com.sakinr.patika.airportreservatinsystem.model.entity.Route;

import java.util.List;

public interface RouteService {

    List<Route> getAllRoutes();

    Route getRoute(String id);

    void addRoute(Route route);

    Route updateRoute(Route route);

    boolean deleteRoute(String id);

    Route getFirstRouteByDepartureAirportByDefault(String departureAirportId);

}
