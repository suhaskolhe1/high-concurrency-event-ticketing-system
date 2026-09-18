package org.suhas.booking.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhas.booking.domain.Booking;
import org.suhas.booking.dto.BookingRequest;
import org.suhas.booking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> bookSeat(@RequestHeader("X-User-Id") String userId,
                                            @RequestBody BookingRequest request) {
  return  ResponseEntity.ok(bookingService.initiateBooking(userId, request));
    }

}
