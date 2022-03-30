package com.sakinr.patika.airportreservationsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airport_company")
public class AirportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name can not be null")
    private String name;

    // Cascade Type sample usage as CascadeType.REMOVE
    @JsonIgnore
    @OneToMany(mappedBy = "airportCompany", cascade = CascadeType.MERGE)
    private List<Flight> flights;

    @Override
    public String toString() {
        return "AirportCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
