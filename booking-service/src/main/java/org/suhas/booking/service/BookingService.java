package org.suhas.booking.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.suhas.booking.domain.Booking;
import org.suhas.booking.dto.BookingRequest;
import org.suhas.booking.kafka.BookingProducer;
import org.suhas.booking.repository.BookingRepository;
import org.suhas.common.event.BookingCreatedEvent;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingProducer bookingProducer;

    @Transactional
    public Booking initiateBooking(String userId, BookingRequest request){

        // 1. Save the booking as PENDING in PostgreSQL
        Booking booking = Booking.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .eventId(request.eventId())
                .seatId(request.seatId())
                .price(request.price())
                .status("PENDING")
                .build();
        booking = bookingRepository.save(booking);


        // 2. Publish event to Kafka to start the Payment Saga!
        BookingCreatedEvent event = new BookingCreatedEvent(
                booking.getId(), booking.getEventId(), booking.getSeatId(), userId, booking.getPrice()
        );
        bookingProducer.sendBookingCreatedEvent(event);

        return  booking;
    }
}
