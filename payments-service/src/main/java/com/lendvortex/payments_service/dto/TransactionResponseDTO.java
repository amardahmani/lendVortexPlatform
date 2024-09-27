package com.lendvortex.payments_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionResponseDTO {
    private Long id;
    private String transactionType;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String description;
    private String externalReference;
    private Long loanId;
    private Long user;
    private UUID cardId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
