package com.clairvoyantsoft.demo;

import com.clairvoyantsoft.demo.domain.*;
import com.clairvoyantsoft.demo.dto.BookingDTO;
import com.clairvoyantsoft.demo.repository.BookingRepository;
import com.clairvoyantsoft.demo.repository.PaymentRepository;
import com.clairvoyantsoft.demo.repository.PropertyRepository;
import com.clairvoyantsoft.demo.repository.UserRepository;
import com.clairvoyantsoft.demo.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Profile("default")
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final PropertyRepository propertyRepository;

    private final PaymentRepository paymentRepository;

    private final BookingService bookingService;

    public DataLoader(UserRepository userRepository,
                      BookingRepository bookingRepository,
                      PropertyRepository propertyRepository,
                      PaymentRepository paymentRepository,
                      BookingService bookingService){
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.paymentRepository = paymentRepository;
        this.bookingService = bookingService;
    }
    @Override
    public void run(String... args) throws Exception {

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testlogin", "", null));
        System.out.println("App started...........................................");

        User user = new User();
        user.setActive(true);
        user.setUsername("testlogin");
        user.setPassword("$2a$10$djv8NsHWWyBdUTBsPvXVA.fbXFseJ/6/tai9HbuMgekskItlrTfbS");

        Role roleUser = new Role();
        roleUser.setName("USER");


        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        user.addRole(roleUser);
        user.addRole(roleAdmin);

        userRepository.save(user);

        Booking booking = new Booking();
        booking.setAmountPaid(BigDecimal.valueOf(250));
        booking.setBookingStatus("CONFIRM");
        booking.setStartDate(Instant.now());
        booking.setEndDate(Instant.now().plus(6, ChronoUnit.HOURS));
        booking.setUser(user);
        booking.setDiscount(BigDecimal.valueOf(0));
        booking.setTax(BigDecimal.valueOf(25));
        booking.setTotal(BigDecimal.valueOf(275));
        booking.setPaymentStatus("COMPLETED");

        Address address = new Address();
        address.setCity("Madgaon");
        address.setState("Goa");
        address.setCountry("India");
        address.setZipcode("411033");

        Property property = new Property();
        property.setName("7 View Resort");
        property.setDescription("Awesome weekend getaway");
        property.setUser(user);
        property.setAddress(address);
        propertyRepository.save(property);

        booking.setProperty(property);


        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(850));
        payment.setCurrency("USD");
        payment.setUser(user);
        payment.setPaymentDate(Instant.now());
        paymentRepository.save(payment);

        bookingRepository.save(booking);


        Foo foo = new Foo();
        foo.setId(1l);

        Bar bar = new Bar();
        bar.setId(2l);
        bar.setFoos(Arrays.asList(foo));
        foo.setBars(Arrays.asList(bar));

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new Hibernate5Module());
        System.out.println(mapper.writeValueAsString(foo));

        System.out.println(mapper.writeValueAsString(userRepository.findUserByUsername("testlogin")));

        System.out.println("Test Transactional booking method");

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingPrize(BigDecimal.valueOf(250));
        bookingDTO.setPropertyId(1l);
        bookingDTO.setStartTime(Instant.now().plus(6, ChronoUnit.HOURS));
        bookingDTO.setEndTime(Instant.now().plus(18, ChronoUnit.HOURS));

        bookingService.bookProperty(bookingDTO);
        System.out.println("End Transactional.");
    }
}
