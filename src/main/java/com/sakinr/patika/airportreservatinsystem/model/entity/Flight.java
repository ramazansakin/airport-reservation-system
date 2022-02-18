package com.sakinr.patika.airportreservatinsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Flight {

    @Id
    private String id;

    @NotNull(message = "code can not be null")
    private String code;

    @NotNull(message = "quota can not be null")
    private Integer quota;

    @NotNull(message = "price can not be null")
    private Integer price;

    @NotNull(message = "departure date can not be null")
    @Column(name = "departure_date")
    private Date departureDate;

    @NotNull(message = "estimated arrival date can not be null")
    @Column(name = "estimated_arrival_date")
    private Date estimatedArrivalDate;

    @DBRef
    private List<Ticket> tickets;

    @NotNull(message = "route can not be null")
    @DBRef
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;


    @NotNull(message = "airport can not be null")
    @DBRef
    @JoinColumn(name = "airport_company_id", referencedColumnName = "id")
    private AirportCompany airportCompany;

//    Sample Reference management - Avoid infinite loop
//    public class User {
//        public int id;
//        public String name;
//
//        @JsonBackReference
//        public List<Item> userItems;
//    }
//
//    public class Item {
//        public int id;
//        public String itemName;
//
//        @JsonManagedReference
//        public User owner;
//    }

}
