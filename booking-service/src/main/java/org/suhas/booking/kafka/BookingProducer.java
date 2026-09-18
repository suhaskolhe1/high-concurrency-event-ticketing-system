package org.suhas.booking.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.suhas.common.event.BookingCreatedEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingProducer {
    private final KafkaTemplate <String,Object> kafkaTemplate;

    public void sendBookingCreatedEvent(BookingCreatedEvent event){
        log.info("Publishing BookingCreatedEvent to Kafka for booking: {}", event.bookingId());
        // The topic name is "booking-created-topic"
        kafkaTemplate.send("booking-created-topic", event);
    }
}
