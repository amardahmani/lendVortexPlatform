package com.lendvortex.loanservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanOfferResponseDTO {

    private int offeringId;
    private Long lenderId;
    private String name;
    private String description;

    private BigDecimal interestRate;
    private BigDecimal totalAmount;
    private BigDecimal remainingAmount;
    private BigDecimal minApplicationAmount;
    private BigDecimal maxApplicationAmount;

    private boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
