package com.creditcard.account.customer.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@RequiredArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Must have First Name")
    @Size(min = 2, max = 26, message = "First Name must be 2 to 26 digits long")
    private String firstName;

    @NotBlank(message = "Must have Last Name")
    @Size(min = 2, max = 26, message = "Last Name must be 2 to 26 digits long")
    private String lastName;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
