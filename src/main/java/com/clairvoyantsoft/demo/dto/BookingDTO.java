package com.clairvoyantsoft.demo.dto;

import com.clairvoyantsoft.demo.projection.BookingView;

import java.math.BigDecimal;
import java.time.Instant;

public class BookingDTO {

    private Long id;

    private Long propertyId;

    private Instant startTime;

    private Instant endTime;

    private BigDecimal bookingPrize;


    public BookingDTO() {

    }

    public BookingDTO(BookingView bookingView) {

        this.bookingPrize = bookingView.getBookingPrize();
        this.startTime = bookingView.getStartDate();
        this.endTime = bookingView.getEndDate();
        this.id = bookingView.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getBookingPrize() {
        return bookingPrize;
    }

    public void setBookingPrize(BigDecimal bookingPrize) {
        this.bookingPrize = bookingPrize;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }


}