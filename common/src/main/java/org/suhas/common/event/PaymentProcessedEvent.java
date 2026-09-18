package org.suhas.common.event;

public record PaymentProcessedEvent(String bookingId, boolean success, String transactionId, String reason) {}
