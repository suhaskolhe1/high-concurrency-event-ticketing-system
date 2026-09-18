package org.suhas.notification.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.suhas.common.event.BookingConfirmedEvent;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "booking-confirmed-topic", groupId = "notification-group")
    public void consumeBookingConfirmed(BookingConfirmedEvent event) {
        // Here you would integrate SendGrid, AWS SES, or Twilio
        log.info("==================================================");
        log.info("📧 SENDING TICKET EMAIL TO USER: {}", event.userId());
        log.info("🎟️ Event ID: {} | Seat ID: {}", event.eventId(), event.seatId());
        log.info("✅ Booking {} is fully confirmed!", event.bookingId());
        log.info("==================================================");
    }
}
