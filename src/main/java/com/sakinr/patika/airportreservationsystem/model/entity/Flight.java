package com.sakinr.patika.airportreservationsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @NotNull(message = "route can not be null")
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;


    @NotNull(message = "airport can not be null")
    @ManyToOne(cascade = CascadeType.MERGE)
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
