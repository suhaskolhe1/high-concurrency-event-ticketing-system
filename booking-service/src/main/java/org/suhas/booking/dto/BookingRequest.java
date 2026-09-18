package org.suhas.booking.dto;

import java.math.BigDecimal;

public record BookingRequest(String eventId, String seatId, BigDecimal price) {}
