package com.example.demo.repo;

import com.example.demo.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Testcontainers
class CustomerRepositoryTest {
    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.4");
    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
    }


    @Test
    public void should_find_customer_by_first_name() {
        var customer_saved = customerRepository.save(new Customer("John", "Doe"));
        var costumer = customerRepository.findById(customer_saved.getId());
        assertThat(costumer).isNotNull();
        assertThat(customer_saved.getFirstName()).isEqualTo("John");
        assertThat(customer_saved.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void should_find_customer_by_last_name() {
        var customer_saved = customerRepository.save(new Customer("John", "Doe"));
        var costumer = customerRepository.findById(customer_saved.getId());
        assertThat(costumer).isNotNull();
        assertThat(customer_saved.getFirstName()).isEqualTo("John");
        assertThat(customer_saved.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void should_find_customer_by_id() {
        var customer_saved = customerRepository.save(new Customer("John", "Doe"));
        var costumer = customerRepository.findById(customer_saved.getId());
        assertThat(costumer).isNotNull();
        assertThat(customer_saved.getFirstName()).isEqualTo("John");
        assertThat(customer_saved.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void should_not_find_customer_by_id() {
        var costumer = customerRepository.findById(UUID.randomUUID().toString());
        assertThat(costumer).isEmpty();
    }


}