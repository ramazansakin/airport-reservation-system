package com.sakinr.patika.airportreservationsystem.service.iml;


import com.sakinr.patika.airportreservationsystem.exception.NotFoundException;
import com.sakinr.patika.airportreservationsystem.model.Address;
import com.sakinr.patika.airportreservationsystem.model.entity.Airport;
import com.sakinr.patika.airportreservationsystem.repository.AirportRepository;
import com.sakinr.patika.airportreservationsystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirport(Integer id) {
        Optional<Airport> byId = airportRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Airport"));
    }

    @Override
    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Integer id, Airport airport) {
        getAirport(id);
        airport.setId(id);
        return airportRepository.save(airport);
    }

    @Override
    public boolean deleteAirport(Integer id) {
        airportRepository.delete(getAirport(id));
        return true;
    }

    // java8 playground again :)
    // ----------------------------------------------------------------------------------
    private List<Address> getAddressCityStartsWith(String prefix) {
        List<Airport> allAirports = getAllAirports();
        return allAirports.stream()
                .map(Airport::getAddresses)
                .flatMap(Collection::stream)
                .distinct()
                .filter(a -> a.getCity().startsWith(prefix))
                .collect(Collectors.toList());
    }

    private void printAllAdressCityStartsWith(String prefix) {
        List<Address> addressCityStartsWith = getAddressCityStartsWith(prefix);
        addressCityStartsWith.stream()
                .map(address -> address.getCity() + "/" + address.getStreetCode() + "/" +
                        address.getBuildingNo())
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private void reduceAddressListToCityNameAndStreetCode() {
        List<Airport> allAirports = getAllAirports();
        String reducedAddressList = allAirports.stream()
                .map(Airport::getAddresses)
                .flatMap(Collection::stream)
                .map(address -> address.getCity() + " " + address.getStreetCode())
                .reduce("", (s1, s2) -> s1 + s2);

        System.out.println("Reduced address List : " + reducedAddressList);
    }

    private String getCombinedAddressOfBoth(Airport airport1, Airport airport2) {
        // Airports check here!
        // I assume that there is nothing bad here :) and enjoy with BiFunction sample
        BiFunction<Airport, Airport, String> function = (a1, a2) -> a1.getAddresses().get(0).getCity() + "-" + a1.getAddresses().get(0).getStreetCode()
                + " ------- " +
                a2.getAddresses().get(0).getCity() + "-" + a2.getAddresses().get(0).getStreetCode();

        // Gets combined Address String
        return function.apply(airport1, airport2);
    }

    private void consumeAirportAddresses(Integer airportId) {
        Airport airport = airportRepository.getById(airportId);

        // Defining a consumer for airport address list
        Consumer<Airport> airportConsumer = air -> air.getAddresses().forEach(System.out::println);

        airportConsumer.accept(airport);
    }


}

