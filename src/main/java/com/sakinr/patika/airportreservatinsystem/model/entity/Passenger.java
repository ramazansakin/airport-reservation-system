package com.sakinr.patika.airportreservatinsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Passenger implements Serializable {

    @Id
    private String id;

    @NotNull(message = "firstname can not be null")
    private String firstname;

    @NotNull(message = "lastname can not be null")
    private String lastname;

    @NotNull(message = "gender can not be null")
    private String gender;

    @NotNull(message = "age can not be null")
    private Integer age;

    @NotNull(message = "phone can not be null")
    private String phone;

    @Email
    private String email;

}
