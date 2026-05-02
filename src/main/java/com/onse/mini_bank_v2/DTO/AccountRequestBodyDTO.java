package com.onse.mini_bank_v2.DTO;

import com.onse.mini_bank_v2.Entity.CustomerEntity;
import com.onse.mini_bank_v2.Enum.AccountStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountRequestBodyDTO {
    private Long customerId;
}
