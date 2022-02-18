package com.sakinr.patika.airportreservatinsystem.service;

import com.sakinr.patika.airportreservatinsystem.model.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    List<Ticket> getAllTickets();

    Ticket getTicket(String id);

    void addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    boolean deleteTicket(String id);

//    Page<Ticket> getRelatedFlightTickets(Pageable pageable, Flight flight);

}
