package com.lendvortex.payments_service.repository;

import com.lendvortex.payments_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
