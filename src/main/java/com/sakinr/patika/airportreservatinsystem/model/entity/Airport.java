package com.sakinr.patika.airportreservatinsystem.model.entity;

import com.sakinr.patika.airportreservatinsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class Airport implements Serializable {

    @Id
    private String id;

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
