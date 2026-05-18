package com.onse.mini_bank_v2.Service;

import com.onse.mini_bank_v2.DTO.CustomerRequestBodyDTO;
import com.onse.mini_bank_v2.DTO.CustomerResponseDTO;
import com.onse.mini_bank_v2.Entity.AccountEntity;
import com.onse.mini_bank_v2.Entity.CustomerEntity;
import com.onse.mini_bank_v2.Enum.AccountStatus;
import com.onse.mini_bank_v2.Repository.CustomerRepository;
import net.bytebuddy.asm.Advice;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Test
    void createCustomerService() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);

        CustomerService customerService = new CustomerService(customerRepository);

        CustomerRequestBodyDTO customerRequestBodyDTO = new CustomerRequestBodyDTO(
                "John",
                "",
                "Doe",
                LocalDate.of(2000, 1, 11),
                "Marikina"
        );

        when(customerRepository.save(any(CustomerEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CustomerResponseDTO customerResponseDTO = customerService.createCustomerService(customerRequestBodyDTO);

        assertEquals("John", customerResponseDTO.getFirstName());
    }

    @Test
    void getAllCustomersService() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);

        List<CustomerEntity> customerEntityList = List.of(
            new CustomerEntity(
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
            ),
            new CustomerEntity(
                    2L,
                    "Jane",
                    "",
                    "Doe",
                    LocalDate.of(2000, 1, 12),
                    "Makati",
                    LocalDate.now(),
                    List.of(
                            AccountEntity.builder()
                            .id(2L)
                            .accountNumber("987654321098")
                            .createdDate(LocalDate.now())
                            .status(AccountStatus.ACTIVE)
                            .amount(BigDecimal.valueOf(12000))
                            .customer(new CustomerEntity())
                            .transactions(Collections.emptyList())
                            .build()
                    )
                )
        );

        when(customerRepository.findAll())
                .thenReturn(customerEntityList);

        assertEquals("John", customerEntityList.get(0).getFirstName());
        assertEquals("Jane", customerEntityList.get(1).getFirstName());
    }
 }
