package com.clairvoyantsoft.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="property_review")
@Data
public class PropertyReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;
}
