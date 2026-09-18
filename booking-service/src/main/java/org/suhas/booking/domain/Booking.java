package org.suhas.booking.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bookings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String id;
    private String userId;
    private String eventId;
    private String seatId;
    private BigDecimal price;
    private String status; // PENDING, CONFIRMED, FAILED

    @CreationTimestamp
    private Instant createdAt;
}