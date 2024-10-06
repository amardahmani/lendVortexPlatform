package com.lendvortex.payments_service.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanDisbursementRequest {
    private Long loanId;
    private BigDecimal amount;
    private Long borrowerId;
}
