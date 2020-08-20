package com.creditcard.account.customer.service;

import com.creditcard.account.customer.model.Customer;
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

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Customer customer, Customer updates) {
        Customer updatedCustomer = customer;
        updatedCustomer.setFirstName(updates.getFirstName());
        updatedCustomer.setLastName(updates.getLastName());
        return repository.save(updatedCustomer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
