package com.sakinr.patika.airportreservationsystem.repository;

import com.sakinr.patika.airportreservationsystem.model.entity.Airport;
import com.sakinr.patika.airportreservationsystem.model.entity.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query(
            "select r from Route r" +
                    " where " +
                    " (:departure_airport is NULL or r.departureAirport = :departure_airport) " +
                    " AND " +
                    " (:arrival_airport is NULL or r.arrivalAirport = :arrival_airport) "
    )
    Page<Route> findByDeparture_airportAndArrival_airport(
            Pageable pageable,
            @Param("departure_airport") Airport departure_airport,
            @Param("arrival_airport") Airport arrival_airport
    );

    default List<Route> findByDeparture_airportIs(Airport departure_airport) {
        // we can add default behaviour here if there is problem with this function
        return new ArrayList<>();
    }

    Route getFirstById(Integer id);

    static String getRouteMessage() {
        // We can define static methods for common useful logics
        return "Hello from routes repo interface";
    }

    Route findByDepartureAirport(Airport departure_airport);

}
