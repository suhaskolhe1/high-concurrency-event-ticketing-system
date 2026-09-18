package org.suhas.common.event;
import java.math.BigDecimal;
public record BookingCreatedEvent(String bookingId, String eventId, String seatId, String userId, BigDecimal price) {}
