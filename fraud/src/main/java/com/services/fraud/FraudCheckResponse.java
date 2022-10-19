package com.services.fraud;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FraudCheckResponse {
    private Boolean isFraudster;
}
