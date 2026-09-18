package org.suhas.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.suhas.common.event.BookingCreatedEvent;
import org.suhas.common.event.PaymentProcessedEvent;
import org.suhas.payment.domain.Transaction;
import org.suhas.payment.kafka.PaymentProducer;
import org.suhas.payment.repository.TransactionRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final TransactionRepository transactionRepository;
    private final PaymentProducer paymentProducer;

    @Transactional
    public void processPayment(BookingCreatedEvent event) {
        // IDEMPOTENCY CHECK: If we already processed this booking, ignore it!
        if (transactionRepository.existsByBookingId(event.bookingId())) {
            return;
        }

        boolean paymentSuccess = mockProcessCreditCard();
        String status = paymentSuccess ? "SUCCESS" : "FAILED";

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .bookingId(event.bookingId())
                .amount(event.price())
                .status(status)
                .build();
        transactionRepository.save(transaction);

        PaymentProcessedEvent resultEvent = new PaymentProcessedEvent(
                event.bookingId(), paymentSuccess, transaction.getId(), paymentSuccess ? "Payment successful" : "Insufficient funds"
        );
        paymentProducer.sendPaymentProcessedEvent(resultEvent);
    }

    private boolean mockProcessCreditCard() {
        return Math.random() > 0.1; // Simulate a 90% success rate
    }
}
