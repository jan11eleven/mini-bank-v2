package com.onse.mini_bank_v2.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String firstName;

    @Column(nullable = true, length = 100)
    private String middleName;

    @Column(length = 100)
    private String lastName;

    private LocalDate birthDate;
    private String address;

    private LocalDate createdDate;

    @OneToMany(mappedBy = "customer")
    private List<AccountEntity> accounts;

}
