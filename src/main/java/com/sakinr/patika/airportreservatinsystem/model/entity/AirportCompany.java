package com.sakinr.patika.airportreservatinsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AirportCompany {

    @Id
    private String id;

    @NotNull(message = "name can not be null")
    private String name;

    // Cascade Type sample usage as CascadeType.REMOVE
    @JsonIgnore
    @DBRef
    private List<Flight> flights;

    @Override
    public String toString() {
        return "AirportCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
