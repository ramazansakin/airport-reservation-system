package com.sakinr.patika.airportreservatinsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Ticket implements Serializable {

    @Id
    private String id;

    @DBRef
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @DBRef
    @JsonBackReference
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
