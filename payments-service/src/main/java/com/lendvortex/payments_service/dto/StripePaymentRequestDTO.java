package com.lendvortex.payments_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StripePaymentRequestDTO {
    private Long amount;
    private String currency;
    private String paymentMethodType;
}
