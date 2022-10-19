package com.services.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FraudCheckResponse {
    private Boolean isFraudster;
}
