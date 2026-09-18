package org.suhas.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.suhas.payment.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    boolean existsByBookingId(String bookingId);
}
