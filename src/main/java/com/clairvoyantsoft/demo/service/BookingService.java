package com.clairvoyantsoft.demo.service;

import com.clairvoyantsoft.demo.domain.Booking;
import com.clairvoyantsoft.demo.domain.Payment;
import com.clairvoyantsoft.demo.domain.Property;
import com.clairvoyantsoft.demo.domain.User;
import com.clairvoyantsoft.demo.dto.BookingDTO;
import com.clairvoyantsoft.demo.projection.BookingView;
import com.clairvoyantsoft.demo.repository.BookingRepository;
import com.clairvoyantsoft.demo.repository.PaymentRepository;
import com.clairvoyantsoft.demo.repository.PropertyRepository;
import com.clairvoyantsoft.demo.repository.UserRepository;
import com.clairvoyantsoft.demo.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final PropertyRepository propertyRepository;

    private final UserRepository userRepository;

    private final PaymentRepository paymentRepository;

    public BookingService(final BookingRepository bookingRepository, final PropertyRepository propertyRepository,
                          final UserRepository userRepository,
                          final PaymentRepository paymentRepository) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }


    public Booking bookProperty() {


        return null;
    }

    public Object searchBooking() {

        return null;
    }

    public List<BookingView> getAllBooking() {
        return this.bookingRepository.findBy(BookingView.class);
    }

    public List<Property> findPropertyByZipCode(String zipcode) {

        return this.propertyRepository.findByAddressZipcode(zipcode);
    }

    public User getUserWithPayments() {

        return this.userRepository.findUserByUsername("testlogin");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Booking bookProperty(BookingDTO bookingDTO) {

        List<Booking> bookings = bookingRepository.findIfBookingExists(bookingDTO.getPropertyId(), bookingDTO.getStartTime(), bookingDTO.getEndTime());

        if (Objects.nonNull(bookings) && bookings.size() > 0) {
            throw new RuntimeException("This property is already booked for requested time period.");
        }


        Property property = propertyRepository.findById(bookingDTO.getPropertyId()).orElseThrow(() -> new RuntimeException("Property doesn't exists."));

        String username=SecurityUtils.getCurrentLoggedInUSer().orElseThrow(()->
            new RuntimeException("User is not logged in"));

        BigDecimal tax = BigDecimal.valueOf(25);
        BigDecimal totalAmount = bookingDTO.getBookingPrize().add(tax);

        User user = userRepository.findUserByUsername(username);

        Booking booking = new Booking();
        booking.setAmountPaid(bookingDTO.getBookingPrize());
        booking.setBookingStatus("PENDING");
        booking.setStartDate(bookingDTO.getStartTime());
        booking.setEndDate(bookingDTO.getEndTime());
        booking.setUser(user);
        booking.setDiscount(BigDecimal.valueOf(0));
        booking.setTax(tax);
        booking.setTotal(totalAmount);
        booking.setPaymentStatus("PENDING");
        booking.setProperty(property);
        bookingRepository.save(booking);

        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(850));
        payment.setCurrency("USD");
        payment.setUser(user);
        payment.setPaymentDate(Instant.now());
        payment.setStatus("COMPLETED");
        paymentRepository.save(payment);

        booking.setBookingStatus("COMPLETED");
        booking.setPaymentStatus("COMPLETED");

        return bookingRepository.save(booking);
    }
}
