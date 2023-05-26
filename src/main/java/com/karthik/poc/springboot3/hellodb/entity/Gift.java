package com.karthik.poc.springboot3.hellodb.entity;

import org.springframework.data.annotation.Id;

public record Gift(@Id Long id, String name) {

}
