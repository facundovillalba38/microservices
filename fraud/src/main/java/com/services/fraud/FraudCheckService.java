package com.services.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckRepository;

    @Autowired
    public FraudCheckService(FraudCheckHistoryRepository fraudCheckRepository){
        this.fraudCheckRepository = fraudCheckRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
