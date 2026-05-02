package com.onse.mini_bank_v2.Controller;

import com.onse.mini_bank_v2.DTO.ResponseDTO;
import com.onse.mini_bank_v2.DTO.TransactionRequestBodyDTO;
import com.onse.mini_bank_v2.DTO.TransactionResponseDTO;
import com.onse.mini_bank_v2.Entity.AccountEntity;
import com.onse.mini_bank_v2.Repository.AccountRepository;
import com.onse.mini_bank_v2.Service.TransactionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public TransactionController(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    @ApiResponse(responseCode = "201", description = "Create Transaction Deposit")
    public ResponseEntity<ResponseDTO<TransactionResponseDTO>> createDepositTransactionApi(
            @RequestBody TransactionRequestBodyDTO body
    ) {

        Optional<AccountEntity> account = accountRepository.findById(body.getAccountId());

        ResponseDTO<TransactionResponseDTO> responseDTO = new ResponseDTO<>();

        if(account.isEmpty()) {
            responseDTO.setMessage("Account Id not found!");
            responseDTO.setBody(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }

        TransactionResponseDTO transactionResponseDTO = transactionService.createDespositTransactionService(body.getAmount(), account.get());

        responseDTO.setBody(transactionResponseDTO);
        responseDTO.setMessage("Successfully created transaction!");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

}
