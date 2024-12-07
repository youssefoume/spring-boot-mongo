package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CustomerControllerTest {
    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.4");

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void getAllCustomers() {

        var customers = testRestTemplate.exchange("/api/v1/all", HttpMethod.GET, null, Customer[].class);
        assertNotNull(customers);
        assertThat(customers.getStatusCode()).isEqualTo(OK);
    }
    @Test
    void should_not_save_customer_when_body_not_valide() {
        var customer = new Customer();
        var response=testRestTemplate.exchange("/api/v1/create", HttpMethod.POST, new HttpEntity<>(customer), Map.class);
        assertNotNull(response);
        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
    }
    @Test
    void should_save_customer_when_body_valid() {
        var customer = new Customer("John", "Doe");
        var response =testRestTemplate.exchange("/api/v1/create", HttpMethod.POST, new HttpEntity<>(customer), Customer.class);
        assertNotNull(response);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("John");
        assertThat(response.getBody().getLastName()).isEqualTo("Doe");
    }


}