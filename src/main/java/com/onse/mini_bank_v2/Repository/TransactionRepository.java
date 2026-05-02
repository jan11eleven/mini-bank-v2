package com.onse.mini_bank_v2.Repository;

import com.onse.mini_bank_v2.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
