package com.clairvoyantsoft.demo.domain;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street_Address;

    private String city;
    private String state;

    private String country;
    private String zipcode;
    private String longitude;

    private String latitude;


}
