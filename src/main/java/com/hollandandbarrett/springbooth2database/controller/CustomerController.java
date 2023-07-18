package com.hollandandbarrett.springbooth2database.controller;

import java.util.List;
import java.util.Optional;

import com.hollandandbarrett.springbooth2database.entity.Customer;
import com.hollandandbarrett.springbooth2database.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    ICustomerRepo customerRepo;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> list = customerRepo.findAll();

            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepo.findById(id);
            if (customer.isPresent()) {
                customerRepo.delete(customer.get());
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}