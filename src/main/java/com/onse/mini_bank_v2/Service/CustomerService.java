package com.onse.mini_bank_v2.Service;

import com.onse.mini_bank_v2.DTO.CustomerRequestBodyDTO;
import com.onse.mini_bank_v2.DTO.CustomerResponseDTO;
import com.onse.mini_bank_v2.Entity.CustomerEntity;
import com.onse.mini_bank_v2.Repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO createCustomerService(CustomerRequestBodyDTO body) {
        CustomerEntity newCustomer = customerRepository.save(CustomerEntity.builder()
                        .id(null)
                        .createdDate(LocalDate.now())
                        .address(body.getAddress())
                        .firstName(body.getFirstName())
                        .middleName(body.getMiddleName())
                        .lastName(body.getLastName())
                        .birthDate(body.getBirthDate())
                .build());

        return CustomerResponseDTO.builder()
                .address(newCustomer.getAddress())
                .createdDate(newCustomer.getCreatedDate())
                .lastName(newCustomer.getLastName())
                .firstName(newCustomer.getFirstName())
                .middle(newCustomer.getMiddleName())
                .lastName(newCustomer.getLastName())
                .birthDate(newCustomer.getBirthDate())
                .build();
    }

    public Page<CustomerResponseDTO> getAllCustomersService(Integer startIndex, Integer batchSize) {

        int page = startIndex / batchSize;

        Page<CustomerEntity> customerDetails = customerRepository.findAll(PageRequest.of(page, batchSize));

        return customerDetails.map(t ->
                CustomerResponseDTO.builder()
                        .address(t.getAddress())
                        .birthDate(t.getBirthDate())
                        .lastName(t.getLastName())
                        .middle(t.getMiddleName())
                        .firstName(t.getFirstName())
                        .createdDate(t.getCreatedDate())
                        .build()
        );

    }
}
