package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer update(Customer customer) {
        Customer customer1 = customerRepository.findById(customer.getId()).orElse(null);
        if (customer1 == null) {
            return customerRepository.save(customer);
        } else {
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            return customerRepository.save(customer1);
        }
    }

    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
