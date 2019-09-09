package com.clairvoyantsoft.demo.controller;

import com.clairvoyantsoft.demo.domain.Property;
import com.clairvoyantsoft.demo.domain.User;
import com.clairvoyantsoft.demo.dto.BookingDTO;
import com.clairvoyantsoft.demo.projection.BookingView;
import com.clairvoyantsoft.demo.search.GenericSearchComponent;
import com.clairvoyantsoft.demo.search.SearchCriteria;
import com.clairvoyantsoft.demo.service.BookingService;

import com.clairvoyantsoft.demo.service.SearchAPIResultAggregator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class APIController {

    private final BookingService bookingService;

    private final GenericSearchComponent genericSearchComponent;

    private final SearchAPIResultAggregator searchAPIResultAggregator;

    public APIController(BookingService bookingService,
                         GenericSearchComponent genericSearchComponent,
                         SearchAPIResultAggregator searchAPIResultAggregator){
        this.bookingService = bookingService;
        this.genericSearchComponent = genericSearchComponent;
        this.searchAPIResultAggregator = searchAPIResultAggregator;
    }

    @RequestMapping(value="/bookings", method = RequestMethod.GET)
    public ResponseEntity<List<BookingDTO>> getAllBooking() {

        List<BookingDTO> list =this.bookingService.getAllBooking().stream().map(b -> new BookingDTO(b)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/property/zipcode/{zipcode}")
    public ResponseEntity<List<Property>> findPropertyByZipCode(@PathVariable String zipcode){

        return new ResponseEntity<>(this.bookingService.findPropertyByZipCode(zipcode),HttpStatus.OK);
    }

    @GetMapping(value = "/user/payment")
    public ResponseEntity<User> getUserWithPayment(){

        return new ResponseEntity<>(this.bookingService.getUserWithPayments(),HttpStatus.OK);
    }


    @PostMapping(value = "/generic/search")
    public ResponseEntity<Iterable<Object>> genericSearch(@RequestBody SearchCriteria searchCriteria, Pageable pageable){

        System.out.println(searchCriteria.toString());
        System.out.println(pageable.toString());
        Page<Object> page=genericSearchComponent.search(searchCriteria,pageable);
        return new ResponseEntity<>(page.getContent(),HttpStatus.OK);
    }

    @GetMapping(value="/getResult")
    public ResponseEntity<Object> getResult(){

        return  new ResponseEntity<>(searchAPIResultAggregator.getResult(),HttpStatus.OK);
    }



}
