package com.lendvortex.loanservice.dto.response;

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
public class LoanResponseDTO {

    private int loanId;
    private int applicationId;
    private Long lenderId;

    private BigDecimal amount;
    private BigDecimal interestRate;
    private int termMonths;

    private Date startDate;
    private Date endDate;

    private String status;
    private String loanType;
}