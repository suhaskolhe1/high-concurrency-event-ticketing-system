package org.suhas.payment.kafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.suhas.common.event.BookingCreatedEvent;
import org.suhas.payment.service.PaymentService;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {
    private final PaymentService paymentService;
    @KafkaListener(topics = "booking-created-topic", groupId = "payment-group")
    public void consumeBookingCreatedEvent(BookingCreatedEvent event) {
        log.info("Received BookingCreatedEvent for booking: {}", event.bookingId());
        paymentService.processPayment(event);
    }
}
