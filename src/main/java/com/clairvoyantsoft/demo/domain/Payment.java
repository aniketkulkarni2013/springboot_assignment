package com.clairvoyantsoft.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
@Entity
@Table(name="payment")
public class Payment {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String currency;

    private String status;

    private Instant paymentDate;

    @ManyToOne
    @JoinColumn(name="fk_user")
    private User user;
}
