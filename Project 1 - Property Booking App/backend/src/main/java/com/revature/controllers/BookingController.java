package com.revature.controllers;

import com.revature.models.BookingModel;
import com.revature.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingModel>> getAllBookings(){
        List<BookingModel> bookings = bookingService.getAllBookings();
        return ResponseEntity.status(200).body(bookings);
    }

    //Handler to book a listed property by property id
    @PostMapping("/bookings")
    public ResponseEntity<String> addBooking(@RequestBody BookingModel booking){
        //Check if property is already booked for input dates
        if(booking.getStatus().equals("canceled")){
            return ResponseEntity.status(400).body("Booking details unavailable.");
        } else {
            BookingModel booked = bookingService.addBooking(booking);

            return ResponseEntity.status(200).body("Booking successful for:" + booked.getBookingId());
        }
    }

/*
    Handlers to retrieve available properties by different params:
        @param: guest_id reference users(user_id)
        @param: property_id
        @param: date range
        @param: Size
        @param: Owner
     */

    @GetMapping("/bookings/{property_id}")
    public ResponseEntity<String> getPropertyByID(@PathVariable Long property_id){
        BookingModel property = bookingService.getBookingByProperty(property_id);
        if(property == null){
            return ResponseEntity.status(404).body("No property found for given id.");
        }
        return ResponseEntity.status(200).body(property.toString());
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long bookingId){
        int deleted = bookingService.deleteBooking(bookingId);
        if (deleted == 1) return ResponseEntity.status(200).body("Deletion successful");
        return ResponseEntity.status(404).body("Deletion unsuccessful");
    }
}
