package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private final CustomerService customerService;
    static Logger logger = Logger.getLogger(CustomerController.class.getName());

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
        logger.info("creating customer successfully "+ customer);
        return ResponseEntity.ok(customerService.create(customer));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/find/lastName/{lastName}")
    public ResponseEntity<List<Customer>> getByLastName(@PathVariable @NotEmpty String lastName) {
        return ResponseEntity.ok(customerService.findByLastName(lastName));
    }

    @GetMapping("/find/firstName/{firstName}")
    public ResponseEntity<List<Customer>> getByFirstName(@PathVariable @NotEmpty String firstName) {
        return ResponseEntity.ok(customerService.findByFirstName(firstName));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Customer> getById(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable @NotEmpty String id) {
        customerService.delete(id);
        return ResponseEntity.ok(String.format("the customer with %s is deleted successfully",id));
    }
}

