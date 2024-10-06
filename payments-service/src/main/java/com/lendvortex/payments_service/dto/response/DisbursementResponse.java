package com.lendvortex.payments_service.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor

public class DisbursementResponse extends PaymentResponse{
    private UUID loanId;
    private Long borrowerId;
    private String bankAccountLast4;
    private String recipientName;
    private DisbursementStatus specificStatus;

    public enum DisbursementStatus {
        INITIATED,
        PROCESSING,
        COMPLETED,
        FAILED,
        CANCELLED;

        public static DisbursementStatus fromStripeTransferStatus(String stripeStatus) {
            return switch (stripeStatus) {
                case "pending" -> PROCESSING;
                case "succeeded" -> COMPLETED;
                case "failed" -> FAILED;
                case "canceled" -> CANCELLED;
                default -> throw new IllegalArgumentException("Unknown Stripe status: " + stripeStatus);
            };
        }
    }
}
