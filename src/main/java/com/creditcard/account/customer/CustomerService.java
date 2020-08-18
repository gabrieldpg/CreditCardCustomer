package com.creditcard.account.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<CustomerModel> getAll() {
        return repository.findAll();
    }

    public Optional<CustomerModel> getById(Long id) {
        return repository.findById(id);
    }

    public CustomerModel createOrUpdate(CustomerModel customer) {
        return repository.save(customer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
