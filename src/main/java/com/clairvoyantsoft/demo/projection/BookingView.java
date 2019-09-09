package com.clairvoyantsoft.demo.projection;

import com.clairvoyantsoft.demo.domain.Property;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.Instant;


public interface BookingView {

    Long getId();

    Instant getStartDate();


    Instant getEndDate();

    @Value("#{target.amountPaid + target.tax - target.discount}")
    BigDecimal getBookingPrize();

}
