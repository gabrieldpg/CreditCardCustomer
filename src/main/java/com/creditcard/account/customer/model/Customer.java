package com.creditcard.account.customer.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 26)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 26)
    private String lastName;

    @NotBlank
    @Past
    private Date dateOfBirth;

    @NotBlank
    @PositiveOrZero
    private int cardLimit;

    @NotBlank
    private int balance;

    @NotBlank
    private int available;

    @NotBlank
    @PositiveOrZero
    private int dueRemaining;

    @NotBlank
    @FutureOrPresent
    private Date nextStatement;

    @NotBlank
    @FutureOrPresent
    private Date nextDueDate;

    @ElementCollection
    private List<Transaction> transactions;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
