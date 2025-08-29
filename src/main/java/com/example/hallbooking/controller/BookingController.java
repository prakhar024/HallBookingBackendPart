//package com.example.hallbooking.controller;
//
//import com.example.hallbooking.model.Booking;
//import com.example.hallbooking.repository.BookingRepository;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/bookings")
//@CrossOrigin(origins = "http://localhost:4200")
//public class BookingController {
//
//    private final BookingRepository bookingRepo;
//
//    public BookingController(BookingRepository bookingRepo) {
//        this.bookingRepo = bookingRepo;
//    }
//
//    @GetMapping
//    public List<Booking> getAllBookings() {
//        return bookingRepo.findAll();
//    }
//
//    @PostMapping
//    public Booking createBooking(@RequestBody Booking booking) {
//        return bookingRepo.save(booking);
//    }
//
//    @PutMapping("/{id}")
//    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
//        Booking booking = bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
//        booking.setCustomerName(bookingDetails.getCustomerName());
//        booking.setCustomerPhone(bookingDetails.getCustomerPhone());
//        booking.setEventDate(bookingDetails.getEventDate());
//        booking.setStatus(bookingDetails.getStatus());
//        booking.setHall(bookingDetails.getHall());
//        booking.setUser(bookingDetails.getUser());
//        booking.setBookingDate(bookingDetails.getBookingDate());
//        return bookingRepo.save(booking);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBooking(@PathVariable Long id) {
//        bookingRepo.deleteById(id);
//    }
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
//     if (!bookingRepo.existsById(id)) {
//        return ResponseEntity.notFound().build(); // 404 if not found
//     }
//     bookingRepo.deleteById(id);
//     return ResponseEntity.noContent().build(); // 204 success
//}
//}
package com.example.hallbooking.controller;

import com.example.hallbooking.dto.BookingRequest;
import com.example.hallbooking.model.Booking;
import com.example.hallbooking.model.Hall;
import com.example.hallbooking.model.User;
import com.example.hallbooking.repository.BookingRepository;
import com.example.hallbooking.repository.HallRepository;
import com.example.hallbooking.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private final BookingRepository bookingRepo;
    private final HallRepository hallRepo;
    private final UserRepository userRepo;

    public BookingController(BookingRepository bookingRepo,
                             HallRepository hallRepo,
                             UserRepository userRepo) {
        this.bookingRepo = bookingRepo;
        this.hallRepo = hallRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest req) {
        Hall hall = hallRepo.findById(req.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found"));
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setCustomerName(req.getCustomerName());
        booking.setCustomerPhone(req.getCustomerPhone());
        booking.setEventDate(LocalDate.parse(req.getEventDate()));
        booking.setBookingDate(LocalDate.parse(req.getBookingDate()));
        booking.setStatus(req.getStatus() != null ? req.getStatus() : "Pending");
        booking.setHall(hall);
        booking.setUser(user);

        return bookingRepo.save(booking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody BookingRequest req) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Hall hall = hallRepo.findById(req.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found"));
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        booking.setCustomerName(req.getCustomerName());
        booking.setCustomerPhone(req.getCustomerPhone());
        booking.setEventDate(LocalDate.parse(req.getEventDate()));
        booking.setBookingDate(LocalDate.parse(req.getBookingDate()));
        booking.setStatus(req.getStatus() != null ? req.getStatus() : booking.getStatus());
        booking.setHall(hall);
        booking.setUser(user);

        return bookingRepo.save(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingRepo.deleteById(id);
    }

}



