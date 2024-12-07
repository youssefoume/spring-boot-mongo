package com.example.demo.repo;


import java.util.List;

import com.example.demo.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);

}