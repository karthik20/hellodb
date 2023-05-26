package com.karthik.poc.springboot3.hellodb.entity;

import org.springframework.data.annotation.Id;

public record Customer(@Id Long id, String name) {

}

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Table(name = "CUSTOMER")
// public class Customer {

// @Id
// private Long id;
// private String name;

// }