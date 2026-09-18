package org.suhas.event.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Event implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private Instant date;
    private String location;
    private Integer totalSeats;
    private Integer availableSeats;
    private BigDecimal price;

    @CreationTimestamp
    private Instant createdAt;
}
