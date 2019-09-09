package com.clairvoyantsoft.demo.controller;

import com.clairvoyantsoft.demo.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/bookingApp")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    @RequestMapping(value="/payment", method = RequestMethod.GET)
    public String showIndex(ModelMap map) {
        System.out.println("payment");
        return "UserPayment";
    }



}
