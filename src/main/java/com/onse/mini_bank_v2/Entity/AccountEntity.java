package com.onse.mini_bank_v2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onse.mini_bank_v2.Enum.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 12)
    private String accountNumber;
    private LocalDate createdDate;
    private AccountStatus status;
    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "account")
    private List<TransactionEntity> transactions;
}
