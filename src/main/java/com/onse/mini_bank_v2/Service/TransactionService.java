package com.onse.mini_bank_v2.Service;

import com.onse.mini_bank_v2.DTO.TransactionRequestBodyDTO;
import com.onse.mini_bank_v2.DTO.TransactionResponseDTO;
import com.onse.mini_bank_v2.Entity.AccountEntity;
import com.onse.mini_bank_v2.Entity.TransactionEntity;
import com.onse.mini_bank_v2.Enum.TransactionStatus;
import com.onse.mini_bank_v2.Enum.TransactionType;
import com.onse.mini_bank_v2.Repository.TransactionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final JdbcTemplate jdbcTemplate;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, JdbcTemplate jdbcTemplate, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.accountService = accountService;
    }

    @Transactional
    public TransactionResponseDTO createDespositTransactionService(BigDecimal amount, AccountEntity account) {
        TransactionEntity newTransaction = transactionRepository.save(
                TransactionEntity.builder()
                        .transactionType(TransactionType.DEPOSIT)
                        .account(account)
                        .amount(amount)
                        .createdDate(LocalDate.now())
                        .status(TransactionStatus.IN_PROGRESS)
                        .transactionNumber(generateTransactionNumber())
                        .build()
        );

        BigDecimal newAmount = accountService.depositAmount(account.getId(), amount);

        return TransactionResponseDTO.builder()
                .amount(newAmount)
                .transactionNumber(newTransaction.getTransactionNumber())
                .build();
    }

    private String generateTransactionNumber() {

        Long generatedSeq = jdbcTemplate.queryForObject(
                "SELECT nextval('transaction_number_seq')",
                Long.class
        );

        String zeroNumbers = "0000000";
        String preWord = "TRXN";

        String newFormat = zeroNumbers.concat(generatedSeq.toString()).substring(generatedSeq.toString().length());

        return preWord.concat(newFormat);
    }
}
