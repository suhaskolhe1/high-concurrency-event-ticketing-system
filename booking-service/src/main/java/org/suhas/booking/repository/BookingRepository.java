package org.suhas.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhas.booking.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking,String> {
}
