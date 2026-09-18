package org.suhas.common.event;
public record BookingConfirmedEvent(String bookingId, String eventId, String seatId, String userId) {}
