package com.onse.mini_bank_v2.Service;

import com.onse.mini_bank_v2.DTO.AccountRequestBodyDTO;
import com.onse.mini_bank_v2.DTO.AccountResponseDTO;
import com.onse.mini_bank_v2.Entity.AccountEntity;
import com.onse.mini_bank_v2.Entity.CustomerEntity;
import com.onse.mini_bank_v2.Enum.AccountStatus;
import com.onse.mini_bank_v2.Repository.AccountRepository;
import com.onse.mini_bank_v2.Repository.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final JdbcTemplate jdbcTemplate;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, JdbcTemplate jdbcTemplate, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.customerRepository = customerRepository;
    }

    public AccountResponseDTO createAccountApi(CustomerEntity customer) {

        Long accountNumber = generateAccountNumber();

        AccountEntity newAccount = accountRepository.save(AccountEntity.builder()
                .accountNumber(accountNumber.toString())
                .createdDate(LocalDate.now())
                .status(AccountStatus.ACTIVE)
                .customer(customer)
                .build());

        return AccountResponseDTO.builder()
                .accountNumber(newAccount.getAccountNumber())
                .status(newAccount.getStatus())
                .createdDate(newAccount.getCreatedDate())
                .build();
    }

    public BigDecimal depositAmount(Long accountId, BigDecimal amount) {

        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found!"));

        account.setAmount(account.getAmount().add(amount));

        AccountEntity updatedAccount = accountRepository.save(account);

        return updatedAccount.getAmount();

    }

    @Transactional
    private Long generateAccountNumber() {

        return jdbcTemplate.queryForObject(
                "SELECT nextval('account_number_seq')",
                Long.class
        );
    }
}
