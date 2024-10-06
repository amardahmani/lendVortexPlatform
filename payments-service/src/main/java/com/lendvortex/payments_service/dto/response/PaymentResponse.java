package com.lendvortex.payments_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
public class PaymentResponse {
    private UUID transactionId;
    private PaymentStatus status;
    private PaymentType type;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime timestamp;
    private String errorCode;
    private String errorMessage;
    private Map<String, String> metadata;

    // Payment provider specific fields
    private String stripePaymentIntentId;
    private String stripeTransferId;

    public enum PaymentStatus {
        SUCCEEDED,
        PENDING,
        FAILED,
        CANCELLED,
        REFUNDED,
        REQUIRES_ACTION,
        PROCESSING
    }

    public enum PaymentType {
        DISBURSEMENT,
        REPAYMENT,
        REFUND,
        FEE
    }
}
