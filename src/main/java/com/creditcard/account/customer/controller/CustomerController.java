package com.creditcard.account.customer.controller;

import com.creditcard.account.customer.model.Customer;
import com.creditcard.account.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/creditcard/account/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Optional<Customer> customer = service.getById(id);
        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Validated Customer customer) {
        return ResponseEntity.ok(service.createOrUpdate(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @Validated Customer customer) {
        Optional<Customer> updatedCustomer = service.getById(id);
        if (!updatedCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedCustomer.get().setFirstName(customer.getFirstName());
        updatedCustomer.get().setLastName(customer.getLastName());
        return ResponseEntity.ok(service.createOrUpdate(updatedCustomer.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        Optional<Customer> customer = service.getById(id);
        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}