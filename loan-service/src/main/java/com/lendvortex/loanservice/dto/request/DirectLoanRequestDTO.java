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
public class DirectLoanRequestDTO {

    private Long borrowerId;
    private BigDecimal amount;
    private String purpose;
}