package com.onse.mini_bank_v2.Repository;

import com.onse.mini_bank_v2.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
