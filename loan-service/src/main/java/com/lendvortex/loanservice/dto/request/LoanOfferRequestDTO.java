package com.lendvortex.loanservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanOfferRequestDTO {

    private Long lenderId;
    private String name;
    private String description;

    private BigDecimal interestRate;

    private BigDecimal totalAmount;
    private BigDecimal minApplicationAmount;
    private BigDecimal maxApplicationAmount;

    private boolean isActive = true;
}