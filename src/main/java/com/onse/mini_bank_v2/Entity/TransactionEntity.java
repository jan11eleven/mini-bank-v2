package com.onse.mini_bank_v2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onse.mini_bank_v2.Enum.TransactionStatus;
import com.onse.mini_bank_v2.Enum.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    @JsonIgnore
    private AccountEntity account;

    private String transactionNumber;
    private BigDecimal amount;
    private LocalDate createdDate;
    private TransactionStatus status;
    private TransactionType transactionType;

}
