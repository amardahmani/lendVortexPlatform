package com.lendvortex.payments_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RepaymentPlanRequestDTO {
    private Long loanId;
    private Long borrowerId;
    private int frequency;
    private BigDecimal amount;
    private LocalDateTime startDate;
    private boolean isActive;


}
