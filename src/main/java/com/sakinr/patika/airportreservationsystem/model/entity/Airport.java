package com.sakinr.patika.airportreservationsystem.model.entity;

import com.sakinr.patika.airportreservationsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "airport")
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "{validation.messages.airport.name}")
    private String name;

    @NotNull(message = "{validation.messages.airport.address}")
    private String address;

    // Transient tells "do not persist this field"
    @Transient
    private List<Address> addresses;

    public List<Address> formatToAddressList() {
        String[] splitedAddresses = address.split("//");
        List<Address> resultFormat = new ArrayList<>();
        Arrays.stream(splitedAddresses).forEach(split -> {
            String[] spliteds = split.trim().split("/");
            resultFormat.add(new Address(spliteds[0], spliteds[1]));
        });
        return resultFormat;
    }

}
