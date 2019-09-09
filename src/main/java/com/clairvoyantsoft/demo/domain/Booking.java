package com.clairvoyantsoft.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name="booking")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Booking {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant startDate;

    private Instant endDate;

    private String bookingStatus;

    private BigDecimal amountPaid;

    private String paymentStatus;

    private BigDecimal discount;

    private BigDecimal total;

    private BigDecimal tax;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="fk_user")
    private  User user;

    @ManyToOne
    @JoinColumn(name="fk_property")
    private Property property;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name="fk_payment")
    private Payment payment;
}
