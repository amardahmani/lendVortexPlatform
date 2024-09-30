package com.lendvortex.payments_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RepaymentPlanResponseDTO {
    private Long repaymentId;
    private Long loanId;
    private Long borrowerId;
    private String frequency;
    private BigDecimal amount;
    private LocalDateTime startDate;
    private LocalDateTime nextDueDate;
    private boolean isActive;
}
