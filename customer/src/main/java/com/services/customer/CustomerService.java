package com.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate){
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();

        // todo check if email valid
        // todo check if email not taken

        // This is to know the Generated ID to use it for the fraud check.
        customerRepository.saveAndFlush(customer);

        // todo check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId().toString()
        );

        if(fraudCheckResponse.getIsFraudster()){
            throw new IllegalStateException("fraudster");
        }

        // todo send notification
    }
}
