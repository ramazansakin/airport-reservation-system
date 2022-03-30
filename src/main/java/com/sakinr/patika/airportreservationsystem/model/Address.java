package com.sakinr.patika.airportreservationsystem.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class Address implements Serializable {
    @NotBlank
    private String city;
    @NotBlank
    private String province;
    private String streetCode;
    private Integer buildingNo;

    public Address(String city, String province) {
        this.city = city;
        this.province = province;
    }

    public String dbFormat() {
        return city + "/" + province;
    }
}
