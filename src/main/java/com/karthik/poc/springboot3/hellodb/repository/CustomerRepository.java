package com.karthik.poc.springboot3.hellodb.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.karthik.poc.springboot3.hellodb.entity.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends R2dbcRepository<Customer, Long> {

    Flux<Customer> findAllByName(final String name);

}
