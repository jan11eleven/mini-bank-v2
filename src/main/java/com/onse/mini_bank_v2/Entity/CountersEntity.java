package com.onse.mini_bank_v2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigInteger;

@Entity(name = "counters")
public class CountersEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 55)
    private String name;
    @Column(nullable = false)
    private BigInteger value;
}
