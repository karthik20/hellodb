package com.karthik.poc.springboot3.hellodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("CUSTOMER_GIFTS")
public record CustomerGifts(@Id Integer id, Integer customerId, Integer giftId) {
}
