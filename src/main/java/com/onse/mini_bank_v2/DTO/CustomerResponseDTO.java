package com.onse.mini_bank_v2.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {
    private String firstName;
    private String middle;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private LocalDate createdDate;
}
