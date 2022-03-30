package com.sakinr.patika.airportreservationsystem.service.iml;

import com.sakinr.patika.airportreservationsystem.exception.NotFoundException;
import com.sakinr.patika.airportreservationsystem.model.entity.Passenger;
import com.sakinr.patika.airportreservationsystem.repository.PassengerRepository;
import com.sakinr.patika.airportreservationsystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> getAllPassengers() {
        // business logic
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassenger(Integer id) {
        Optional<Passenger> byId = passengerRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Passenger"));
    }

    @Override
    public void addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public boolean deletePassenger(Integer id) {
        passengerRepository.delete(getPassenger(id));
        return true;
    }

    // Java8 Playground
    @Override
    public List<Passenger> getPassengersNameStartsWith(String prefix) {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .filter(p -> p.getFirstname().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Passenger> getPassengersSortedViaLastNameAsUpperCase() {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .sorted(Comparator.comparing(Passenger::getLastname))
                .peek(p -> p.setLastname(p.getLastname().toUpperCase()))
                .collect(Collectors.toList());
    }

    private Passenger getTheOldestMalePassengerAndLowerCaseFirstLast() {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .max(Comparator.comparing(Passenger::getAge))
                .filter(p -> p.getGender().equals("male"))
                .orElseThrow(() -> new NotFoundException("No matching passenger"));
    }

    private Boolean isAnyPassengerLastNameStartsWithCharAndFemale(String prefix) {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .anyMatch(p -> p.getLastname().startsWith(prefix) && p.getGender().equals("female"));
    }

    private Boolean isAllPassengerFemaleAndAgeBetween(Integer minAge, Integer maxAge) {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .allMatch(p -> p.getGender().equals("female") && (p.getAge() > minAge && p.getAge() < maxAge));
    }

    private Boolean isNonePassengerFirstNameAndPhoneStartsWith(String firstName, String phonePrefix) {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .noneMatch(p -> p.getFirstname().equals(firstName) && p.getPhone().startsWith(phonePrefix));
    }

    private Integer getCountOfMalePassengersAgeBetween(Integer minAge, Integer maxAge) {
        List<Passenger> allPassengers = getAllPassengers();
        return (int) allPassengers.stream()
                .filter(p -> p.getGender().equals("male") && (p.getAge() > minAge && p.getAge() < maxAge))
                .count();
    }

    private List<String> getPassengerListAsFirstNameAndLastName() {
        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .map(p -> p.getFirstname() + " " + p.getLastname())
                .collect(Collectors.toList());
    }


}
