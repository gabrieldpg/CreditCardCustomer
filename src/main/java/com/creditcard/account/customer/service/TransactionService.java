package com.creditcard.account.customer.service;

import com.creditcard.account.customer.model.Transaction;
import com.creditcard.account.customer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getAll() {
        return repository.findAll();
    }

    public Optional<Transaction> getById(Long id) {
        return repository.findById(id);
    }

    public Transaction create(Transaction transaction) {
        return repository.save(transaction);
    }

    public Transaction update(Transaction transaction, Transaction updates) {
        Transaction updatedTransaction = transaction;
        /*updatedCustomer.setFirstName(updates.getFirstName());
        updatedCustomer.setLastName(updates.getLastName());*/
        return repository.save(updatedTransaction);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
