package com.karthik.poc.springboot3.hellodb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.karthik.poc.springboot3.hellodb.entity.Customer;
import com.karthik.poc.springboot3.hellodb.repository.CustomerGiftRepository;
import com.karthik.poc.springboot3.hellodb.repository.CustomerRepository;
import com.karthik.poc.springboot3.hellodb.repository.GiftRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private GiftRepository giftRepository;

    public CustomerService(CustomerRepository customerRepository, GiftRepository giftRepository,
            CustomerGiftRepository customerGiftRepository) {
        this.customerRepository = customerRepository;
        this.giftRepository = giftRepository;
        this.customerGiftRepository = customerGiftRepository;
    }

    private CustomerGiftRepository customerGiftRepository;

    public Mono<Customer> save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Flux<Customer> getAllCustomersByName(final String name) {
        return customerRepository.findAllByName(name);
    }

    public Mono<Customer> getCustomerById(final Long id) {
        return customerRepository.findById(id);
    }

    public record CustomerWithGifts(Long customerId, String customerName, List<String> giftsName) {
    }

    public Flux<CustomerWithGifts> getCustomerGifts() {
        var customerGifts = customerGiftRepository.findAll();
        // customerId, giftids

        var groupedCustomerIdToGifts = customerGifts.groupBy(customerGift -> customerGift.customerId());
        var groupedCustomerIdToGiftIds = groupedCustomerIdToGifts.map(t -> {
            return Tuples.of(t.key().longValue(), t.distinct().map(g -> g.giftId().longValue()));
        });
        return groupedCustomerIdToGiftIds.flatMap(t -> {
            var customer = customerRepository.findById(t.getT1());
            var gifts = giftRepository.findAllById(t.getT2());
            var giftNames = gifts.collectList().map(gift -> gift.stream().map(g -> g.name()).toList());
            return customer.zipWith(giftNames);
        }).map(t -> new CustomerWithGifts(t.getT1().id(), t.getT1().name(), t.getT2()));
    }
}
