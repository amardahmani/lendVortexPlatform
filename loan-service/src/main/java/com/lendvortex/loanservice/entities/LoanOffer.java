package com.lendvortex.loanservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "LenderLoanOfferings")
@NoArgsConstructor
@Setter
@Getter
public class LoanOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offeringId;

    @Column(name = "lender_id", nullable = false)
    private Long lender;

    private String name;

    private String description;

    private BigDecimal interestRate;

    private BigDecimal totalAmount;

    private BigDecimal remainingAmount;

    private BigDecimal minApplicationAmount;

    private BigDecimal maxApplicationAmount;

    private boolean isActive = true;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
