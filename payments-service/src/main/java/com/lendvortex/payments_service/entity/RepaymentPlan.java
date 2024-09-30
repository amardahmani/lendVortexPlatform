package com.lendvortex.payments_service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "repayments")
@NoArgsConstructor
@Getter
@Setter
public class RepaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repayment_id",nullable = false,updatable = false)
    private Long repaymentId;

    @Column(name = "loan_id",nullable = false)
    private Long loanId;

    @Column(name = "borrower_id",nullable = false)
    private Long borrowerId;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private BigDecimal amount;

    private LocalDateTime startDate;

    private LocalDateTime nextDueDate;

    @Column(name = "is_active",nullable = false)
    private boolean isActive;



    private enum Frequency{
        DAILY, WEEKLY, BI_WEEKLY, MONTHLY, QUARTERLY, ANNUALLY
    }

    public void calculateNextDueDate() {
        switch (frequency) {
            case DAILY:
                nextDueDate = startDate.plusDays(1);
                break;
            case WEEKLY:
                nextDueDate = startDate.plusWeeks(1);
                break;
            case BI_WEEKLY:
                nextDueDate = startDate.plusWeeks(2);
                break;
            case MONTHLY:
                nextDueDate = startDate.plusMonths(1);
                break;
            case QUARTERLY:
                nextDueDate = startDate.plusMonths(3);
                break;
            case ANNUALLY:
                nextDueDate = startDate.plusYears(1);
                break;
        }
    }

}
