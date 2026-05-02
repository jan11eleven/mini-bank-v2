package com.onse.mini_bank_v2.DTO;

import com.onse.mini_bank_v2.Enum.AccountStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AccountResponseDTO {
    private String accountNumber;
    private LocalDate createdDate;
    private AccountStatus status;
}
