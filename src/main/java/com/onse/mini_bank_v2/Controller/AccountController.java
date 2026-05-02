package com.onse.mini_bank_v2.Controller;

import com.onse.mini_bank_v2.DTO.AccountRequestBodyDTO;
import com.onse.mini_bank_v2.DTO.AccountResponseDTO;
import com.onse.mini_bank_v2.DTO.ResponseDTO;
import com.onse.mini_bank_v2.Entity.CustomerEntity;
import com.onse.mini_bank_v2.Repository.CustomerRepository;
import com.onse.mini_bank_v2.Service.AccountService;
import com.onse.mini_bank_v2.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    public AccountController(CustomerRepository customerRepository, AccountService accountService) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<AccountResponseDTO>> createAccountApi(@RequestBody AccountRequestBodyDTO body) {
        Long customerId = body.getCustomerId();

        ResponseDTO<AccountResponseDTO> responseDTO = new ResponseDTO<>();

        Optional<CustomerEntity> customer = customerRepository.findById(customerId);

        if(customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        AccountResponseDTO accountResponseDTO = accountService.createAccountApi(customer.get());

        responseDTO.setBody(accountResponseDTO);
        responseDTO.setMessage("Successfully created an account!");

        
        return ResponseEntity.ok(responseDTO);
    }

}
