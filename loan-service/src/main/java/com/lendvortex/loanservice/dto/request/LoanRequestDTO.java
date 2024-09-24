package com.lendvortex.loanservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDTO {

    private int applicationId;
    private Long lenderId;

    private BigDecimal amount;
    private BigDecimal interestRate;
    private int termMonths;

    private Date startDate;
    private Date endDate;

    private String loanType;
}