package org.suhas.event.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record EventRequest(String name, String description, Instant date, String location, Integer totalSeats,
                           BigDecimal price) {
}
