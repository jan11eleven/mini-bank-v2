package com.onse.mini_bank_v2.Service;

import com.onse.mini_bank_v2.DTO.AccountResponseDTO;
import com.onse.mini_bank_v2.Entity.AccountEntity;
import com.onse.mini_bank_v2.Entity.CustomerEntity;
import com.onse.mini_bank_v2.Enum.AccountStatus;
import com.onse.mini_bank_v2.Repository.AccountRepository;
import com.onse.mini_bank_v2.Repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Test
    void createAccountTest() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

        AccountService accountService = new AccountService(accountRepository, jdbcTemplate, customerRepository);

        CustomerEntity customer = new CustomerEntity(
                1L,
                "John",
                "",
                "Doe",
                LocalDate.of(2000, 1, 11),
                "Pasig",
                LocalDate.now(),
                List.of(
                        AccountEntity.builder()
                                .id(1L)
                                .accountNumber("123456789012")
                                .createdDate(LocalDate.now())
                                .status(AccountStatus.ACTIVE)
                                .amount(BigDecimal.valueOf(5000))
                                .customer(new CustomerEntity())
                                .transactions(Collections.emptyList())
                                .build()
                )
        );

        when(accountRepository.save(any(AccountEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(jdbcTemplate.queryForObject(
                "SELECT nextval('account_number_seq')",
                Long.class
        )).thenReturn(123456789012L);

        AccountResponseDTO accountResponseDTO = accountService.createAccountApi(customer);

        assertEquals("123456789012", accountResponseDTO.getAccountNumber());


    }
}
