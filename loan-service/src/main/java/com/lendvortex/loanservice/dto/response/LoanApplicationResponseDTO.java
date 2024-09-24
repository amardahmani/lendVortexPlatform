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
public class LoanApplicationResponseDTO {

    private int applicationId;
    private Long borrowerId;
    private Long loanOfferId;
    private BigDecimal amount;
    private String purpose;

    private String applicationType;
    private String status;
    private Timestamp applicationDate;
    private Timestamp decisionDate;

    private String loanType;
}