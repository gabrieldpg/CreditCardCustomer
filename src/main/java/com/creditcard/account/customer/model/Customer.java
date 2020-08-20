package com.creditcard.account.customer.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

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

    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotNull
    @PositiveOrZero
    private double cardLimit;

    @NotNull
    private double balance;

    private double available;

    @NotNull
    @PositiveOrZero
    private double dueRemaining;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate nextStatement;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate nextDueDate;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public void setBalance(double balance) {
        this.balance = balance;
        this.available = this.cardLimit - balance;
    }
}
