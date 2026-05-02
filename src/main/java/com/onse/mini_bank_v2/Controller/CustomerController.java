package com.onse.mini_bank_v2.Controller;

import com.onse.mini_bank_v2.DTO.CustomerResponseDTO;
import com.onse.mini_bank_v2.DTO.CustomerRequestBodyDTO;
import com.onse.mini_bank_v2.DTO.ResponseDTO;
import com.onse.mini_bank_v2.Entity.CustomerEntity;
import com.onse.mini_bank_v2.Service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<CustomerResponseDTO>> createCustomerApi(@RequestBody CustomerRequestBodyDTO body) {

        CustomerResponseDTO responseCustomer = customerService.createCustomerService(body);

        ResponseDTO<CustomerResponseDTO> responseDTO = new ResponseDTO<>();

        responseDTO.setBody(responseCustomer);
        responseDTO.setMessage("Successfully created new customer!");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<Page<CustomerResponseDTO>>> getAllCustomersApi(@RequestParam(defaultValue = "1") Integer startIndex,
                                                                                     @RequestParam(defaultValue = "10") Integer batchSize) {

        Page<CustomerResponseDTO> customerData = customerService.getAllCustomersService(startIndex, batchSize);

        ResponseDTO<Page<CustomerResponseDTO>> responseDTO = new ResponseDTO<>();

        responseDTO.setMessage("Successfully fetch!");
        responseDTO.setBody(customerData);

        return ResponseEntity.ok(responseDTO);

    }
}
