package com.sakinr.patika.airportreservatinsystem.repository;

import com.sakinr.patika.airportreservatinsystem.model.entity.Flight;
import com.sakinr.patika.airportreservatinsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservatinsystem.model.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

// JPA -Hibernate self-created query apis
@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> getByPassenger(Passenger passenger);

    List<Ticket> getByFlight(Flight flight);

    List<Ticket> getAllByFlight_DepartureDateBetween(Date startDate, Date endDate);

    //JPQL - Java Persistence Query Language
//    @Query(
//            value = "SELECT t FROM Ticket t " +
//                    " WHERE (:flight is NULL or :flight = t.flight) " +
//                    " ORDER BY t.id ")
//    Page<Ticket> getAllByFlightPagination(
//            Pageable pageable,
//            @Param("flight") Flight flight
//    );

    Page<Ticket> getAllByFlightOrderById(Pageable pageable, Flight flight);

}
