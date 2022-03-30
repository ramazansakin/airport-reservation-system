package com.sakinr.patika.airportreservationsystem.service.iml;

import com.sakinr.patika.airportreservationsystem.exception.NotFoundException;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.repository.PassengerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerServiceImpl passengerService;

//    @BeforeEach
//    public void setup() {
//
//        // init
//        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");
//
//        // stub - when
//        Mockito.when(passengerRepository.findById(1)).thenReturn(Optional.of(expectedPassenger));
//    }

    @Test
    void getAllPassengers() {
        // init step
        Passenger passenger1 = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");
        Passenger passenger2 = new Passenger(2, "Passenger2", "L2", "Female", 35, "05554443311", "p2@mail.com");
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger1);
        passengers.add(passenger2);

        // stub - when
        when(passengerRepository.findAll()).thenReturn(passengers);

        // then
        List<Passenger> allPassengers = passengerService.getAllPassengers();

        Assert.assertEquals(passengers.size(), allPassengers.size());
    }

    @Test
    void getPassenger_successful() {
        // init step
        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");

        // stub - when step
        Optional<Passenger> expectedOptPassenger = Optional.of(expectedPassenger);
        when(passengerRepository.findById(1)).thenReturn(expectedOptPassenger);

        // then step
        Passenger actualPassenger = passengerService.getPassenger(1);

        // valid step
        assertEquals(expectedPassenger, actualPassenger);
    }

    @Test
    void getPassenger_not_found() {
        // stub - when step
        when(passengerRepository.findById(1)).thenReturn(Optional.empty());

        // then step
        assertThrows(NotFoundException.class,
                () -> {
                    Passenger actualPassenger = passengerService.getPassenger(1);
                }
        );

    }

    @Test
    void addPassenger() {
        // init
        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");

        // stub - when
        when(passengerRepository.save(expectedPassenger)).thenReturn(expectedPassenger);

        // then
        passengerService.addPassenger(expectedPassenger);
//        Passenger byId = passengerRepository.getById(1);
//
//        Assert.assertEquals(expectedPassenger, byId);

        verify(passengerRepository, times(1)).save(expectedPassenger);
    }

    @Test
    void updatePassenger() {
    }

    @Test
    void deletePassenger() {
    }

    @Test
    void getPassengersNameStartsWith() {
    }

    @Test
    void getPassengersSortedViaLastNameAsUpperCase() {
    }
}