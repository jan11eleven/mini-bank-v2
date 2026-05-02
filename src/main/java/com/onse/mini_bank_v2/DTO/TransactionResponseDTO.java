package com.onse.mini_bank_v2.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionResponseDTO {
    private BigDecimal amount;
    private String transactionNumber;
}
