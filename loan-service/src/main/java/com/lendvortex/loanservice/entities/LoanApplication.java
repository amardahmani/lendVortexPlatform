package com.lendvortex.loanservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "loan_applications")
@NoArgsConstructor
@Getter
@Setter
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;


    @Column(name = "borrower_id", nullable = false)
    private Long borrower;

    @ManyToOne
    @JoinColumn(name = "offering_id")
    private LoanOffer offering;

    private BigDecimal amount;

    private String purpose;

    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    private Timestamp applicationDate;

    private Timestamp decisionDate;


    public enum ApplicationType {
        DIRECT,
        MARKETPLACE
    }

    public enum ApplicationStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
