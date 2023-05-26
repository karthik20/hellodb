package com.karthik.poc.springboot3.hellodb.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.karthik.poc.springboot3.hellodb.entity.CustomerGifts;

public interface CustomerGiftRepository extends R2dbcRepository<CustomerGifts, Integer> {

}
