package com.sakinr.patika.airportreservatinsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "airport_company")
public class AirportCompany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name can not be null")
    private String name;

    // Cascade Type sample usage as CascadeType.REMOVE
    @JsonBackReference
    @OneToMany(mappedBy = "airportCompany", cascade = CascadeType.MERGE)
    private List<Flight> flights;

}
