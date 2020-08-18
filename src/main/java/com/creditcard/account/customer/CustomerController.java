package com.creditcard.account.customer;

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
    public List<CustomerModel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getById(@PathVariable Long id) {
        Optional<CustomerModel> customer = service.getById(id);
        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

    @PostMapping
    public ResponseEntity<CustomerModel> create(@Validated CustomerModel customer) {
        return ResponseEntity.ok(service.createOrUpdate(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> update(@PathVariable Long id, @Validated CustomerModel customer) {
        Optional<CustomerModel> updatedCustomer = service.getById(id);
        if (!updatedCustomer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedCustomer.get().setFirstName(customer.getFirstName());
        updatedCustomer.get().setLastName(customer.getLastName());
        return ResponseEntity.ok(service.createOrUpdate(updatedCustomer.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        Optional<CustomerModel> customer = service.getById(id);
        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
