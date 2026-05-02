package com.onse.mini_bank_v2.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequestBodyDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
}
