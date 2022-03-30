package com.sakinr.patika.airportreservationsystem.service.iml;

import com.sakinr.patika.airportreservationsystem.model.entity.AirportCompany;
import com.sakinr.patika.airportreservationsystem.model.entity.Flight;
import com.sakinr.patika.airportreservationsystem.model.entity.Ticket;
import com.sakinr.patika.airportreservationsystem.repository.AirportCompanyRepository;
import com.sakinr.patika.airportreservationsystem.service.FlightService;
import com.sakinr.patika.airportreservationsystem.service.PassengerService;
import com.sakinr.patika.airportreservationsystem.service.TicketService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AirportCompanyserviceTest {

    @Mock
    private AirportCompanyRepository airportCompanyRepository;

    @Mock
    private FlightService flightService;

    @Mock
    private TicketService ticketService;

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private AirportCompanyServiceImpl airportCompanyService;


    @Test
    void getAllAirportCompanies() {
        // init step
        List<AirportCompany> expectedAirportCompanies = Arrays.asList(
                new AirportCompany(1, "Airport X", null),
                new AirportCompany(2, "Airport Y", null),
                new AirportCompany(3, "Airport Z", null)
        );

        // stub - when
        when(airportCompanyRepository.findAll()).thenReturn(expectedAirportCompanies);

        // then
        List<AirportCompany> allAirportCompanies = airportCompanyService.getAllAirportCompanies();

        Assert.assertEquals(expectedAirportCompanies.size(), allAirportCompanies.size());
        for (AirportCompany expected : expectedAirportCompanies) {
            Optional<AirportCompany> actual = allAirportCompanies
                    .stream().filter(
                            airportCompany -> airportCompany.getId() == expected.getId()
                    ).findFirst();
            Assert.assertEquals(expected.getName(), actual.get().getName());
            Assert.assertEquals(expected.getFlights(), actual.get().getFlights());
        }

    }


    @Test
    void addNewFlight() {
        // expected data init
        AirportCompany airport_x = new AirportCompany(1, "Airport X", new ArrayList<>()); // new ArrayList<>();
        Flight expectedFlight = new Flight(1, "Flight-XYZ", 50, 300, new Date(), new Date(), null, null, airport_x);
        AirportCompany updatedAirport_X = new AirportCompany(1, "Airport X", Arrays.asList(expectedFlight));

        // stub - when
        when(airportCompanyRepository.findById(any())).thenReturn(Optional.of(airport_x));
        when(flightService.getFlight(any())).thenReturn(expectedFlight);
        when(airportCompanyRepository.save(airport_x)).thenReturn(updatedAirport_X);

        // then
        boolean actualStatus = airportCompanyService.addNewFlight(1, 1);

        Assert.assertTrue(actualStatus);
    }

    @Test
    void cancelTicket() {
        // init
        List<Ticket> tickets = new ArrayList<>();
        Flight testFlight = new Flight(1, "Flight-XYZ", 50, 300, new Date(), new Date(),
                tickets, null, null);

        Ticket ticket1 = new Ticket(2, null, testFlight);
        Ticket ticket2 = new Ticket(3, null, testFlight);
        tickets.add(ticket1);
        tickets.add(ticket2);

        testFlight.getTickets().remove(1);
        Flight updatedTestFlight = testFlight;


        // when
        Mockito.when(ticketService.getTicket(any())).thenReturn(ticket1);
        Mockito.when(flightService.getFlight(any())).thenReturn(testFlight);
        Mockito.when(flightService.updateFlight(any())).thenReturn(updatedTestFlight);
        Mockito.when(ticketService.deleteTicket(any())).thenReturn(true);

        // then test
        boolean actualCancelStatus = airportCompanyService.cancelTicket(2);

        // validate
        Assert.assertTrue(actualCancelStatus);

    }

}
