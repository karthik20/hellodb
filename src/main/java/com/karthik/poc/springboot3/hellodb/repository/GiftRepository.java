package com.karthik.poc.springboot3.hellodb.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.karthik.poc.springboot3.hellodb.entity.Gift;

public interface GiftRepository extends R2dbcRepository<Gift, Long> {

}