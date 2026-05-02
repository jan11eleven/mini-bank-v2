package com.onse.mini_bank_v2.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CustomerResponseDTO {
    private String firstName;
    private String middle;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private LocalDate createdDate;
}
