package org.suhas.booking.kafka;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.suhas.booking.domain.Booking;
import org.suhas.booking.repository.BookingRepository;
import org.suhas.common.event.PaymentProcessedEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingConsumer {
    private final BookingRepository bookingRepository;

    @KafkaListener(topics = "payment-processed-topic",groupId = "booking-group")
    @Transactional
    public void consumePaymentEvent(PaymentProcessedEvent event){
        log.info("Received PaymentProcessedEvent for booking: {}", event.bookingId());

        Booking booking = bookingRepository.findById(event.bookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (event.success()) {
            booking.setStatus("CONFIRMED");
            log.info("Booking {} successfully CONFIRMED!", event.bookingId());
        } else {
            booking.setStatus("FAILED");
            log.error("Booking {} FAILED. Reason: {}", event.bookingId(), event.reason());
        }

        bookingRepository.save(booking);
    }

}
