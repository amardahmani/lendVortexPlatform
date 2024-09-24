package com.lendvortex.loanservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "loans")
@NoArgsConstructor
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication application;


    @Column(name = "lender_id")
    private Long lender;

    private BigDecimal amount;

    private BigDecimal interestRate;

    private int termMonths;

    private Date startDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;


    public enum LoanStatus {
        ACTIVE,
        PAID,
        DEFAULTED
    }

    public enum LoanType {
        DIRECT,
        MARKETPLACE
    }
}
