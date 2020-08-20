package com.creditcard.account.customer.controller;

import com.creditcard.account.customer.model.Transaction;
import com.creditcard.account.customer.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/creditcard/account/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    TransactionService service;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Long id) {
        Optional<Transaction> transaction = service.getById(id);
        if (!transaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction.get());
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@Valid Transaction transaction) {
        return ResponseEntity.ok(service.createOrUpdate(transaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @Valid Transaction updatedTransaction) {
        Optional<Transaction> transaction = service.getById(id);
        if (!transaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.createOrUpdate(updatedTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        Optional<Transaction> transaction = service.getById(id);
        if (!transaction.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
