package com.karthik.poc.springboot3.hellodb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.poc.springboot3.hellodb.service.CustomerService;
import com.karthik.poc.springboot3.hellodb.service.CustomerService.CustomerWithGifts;

import reactor.core.publisher.Flux;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public Flux<CustomerWithGifts> getAllCustomersWithGifts() {
        return customerService.getCustomerGifts();
    }

}
