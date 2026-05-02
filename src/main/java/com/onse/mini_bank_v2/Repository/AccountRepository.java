package com.onse.mini_bank_v2.Repository;

import com.onse.mini_bank_v2.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
