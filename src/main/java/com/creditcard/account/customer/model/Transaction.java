package com.creditcard.account.customer.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@RequiredArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String status;    // pending, declined, approved, cancelled

    @NotBlank
    private String type;      // credit or debit

    @NotBlank
    @PositiveOrZero
    private int originalAmount;

    private int currentAmount;
}
