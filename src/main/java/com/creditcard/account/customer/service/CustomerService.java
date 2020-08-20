package com.creditcard.account.customer.service;

import com.creditcard.account.customer.model.Customer;
import com.creditcard.account.customer.model.Transaction;
import com.creditcard.account.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Optional<Customer> getById(Long id) {
        return repository.findById(id);
    }

    public Customer createOrUpdate(Customer customer) {
        return repository.save(customer);
    }

    public Customer addTransaction(Customer customer, Transaction transaction) {
        switch (transaction.getType()) {
            case "credit":
                customer.setBalance(customer.getBalance() - transaction.getOriginalAmount());
                customer.setAvailable(customer.getCardLimit() - customer.getBalance());
                if (customer.getDueRemaining() > 0) {
                    int newDueRemaining = customer.getDueRemaining() - transaction.getOriginalAmount();
                    if (newDueRemaining < 0) {
                        newDueRemaining = 0;
                    }
                    customer.setDueRemaining(newDueRemaining);
                }
                transaction.setStatus("approved");
                break;
            case "debit":
                if (customer.getAvailable() < transaction.getOriginalAmount()) {
                    transaction.setStatus("declined");
                } else {
                    customer.setBalance(customer.getBalance() + transaction.getOriginalAmount());
                    customer.setAvailable(customer.getCardLimit() - customer.getBalance());
                    transaction.setStatus("approved");
                }
                break;
            default:
        }
        List<Transaction> transactions = customer.getTransactions();
        transactions.add(transaction);
        return repository.save(customer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
