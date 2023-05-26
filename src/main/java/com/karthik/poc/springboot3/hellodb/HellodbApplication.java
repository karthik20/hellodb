package com.karthik.poc.springboot3.hellodb;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.karthik.poc.springboot3.hellodb.entity.Customer;
import com.karthik.poc.springboot3.hellodb.repository.CustomerGiftRepository;
import com.karthik.poc.springboot3.hellodb.repository.CustomerRepository;
import com.karthik.poc.springboot3.hellodb.repository.GiftRepository;
import com.karthik.poc.springboot3.hellodb.service.CustomerService;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "com.karthik.poc.springboot3.hellodb.repository.redis")
public class HellodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellodbApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> readyEvent(CustomerRepository customerRepository,
			GiftRepository giftRepository, CustomerGiftRepository customerGiftRepository,
			CustomerService customerService) {
		return event -> {
			List<Customer> customers = Map.of(
					1, "Karthik",
					2, "Avyukt",
					3, "Ashwat", 4, "Srividya")
					.entrySet()
					.stream().map(t -> new Customer(t.getKey().longValue(), t.getValue()))
					.collect(Collectors.toList());
			// customerRepository.saveAll(customers).blockLast(Duration.ofSeconds(2));
			giftRepository.findAll().subscribe(System.out::println);
			customerRepository.findAll().subscribe(System.out::println);
			customerGiftRepository.findAll().subscribe(System.out::println);
		};
	}
}
