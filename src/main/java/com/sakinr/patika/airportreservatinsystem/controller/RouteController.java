package com.sakinr.patika.airportreservatinsystem.controller;


import com.sakinr.patika.airportreservatinsystem.model.Route;
import com.sakinr.patika.airportreservatinsystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RouteService routeService;

    @GetMapping(value = "/all")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/{id}")
    public Route getRoute(@PathVariable @Min(1) Integer id) {
        return routeService.getRoute(id);
    }

    @PostMapping(value = "/create")
    public void saveRoute(@Valid @RequestBody Route route) {
        routeService.addRoute(route);
    }

    @PutMapping(value = "/update")
    public Route updateRoute(@Valid @RequestBody Route route) {
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteRoute(@RequestParam @Min(1) Integer id) {
        return routeService.deleteRoute(id);
    }

    @GetMapping(value = "/v2/departure-airport/{dep_id}")
    public ResponseEntity<Route> getOneByDepartureIdV2(
            @PathVariable @Min(1) Integer dep_id
    ) {
        Route route = routeService.getFirstRouteByDepartureAirportByDefault(dep_id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

}


