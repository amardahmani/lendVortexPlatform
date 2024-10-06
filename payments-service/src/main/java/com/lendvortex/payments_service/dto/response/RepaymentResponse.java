package com.lendvortex.payments_service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class RepaymentResponse extends PaymentResponse{
    private UUID loanId;
    private Long borrowerId;
    private String cardLast4;
    private RepaymentStatus specificStatus;
    private Integer attemptCount;
    private LocalDateTime nextAttemptDate;

    public enum RepaymentStatus {
        AUTHORIZED,
        PROCESSING,
        COMPLETED,
        FAILED,
        REQUIRES_ACTION,
        CANCELLED;

        public static RepaymentStatus fromStripePaymentIntentStatus(String stripeStatus) {
            return switch (stripeStatus) {
                case "requires_payment_method" -> FAILED;
                case "requires_confirmation" -> AUTHORIZED;
                case "requires_action" -> REQUIRES_ACTION;
                case "processing" -> PROCESSING;
                case "succeeded" -> COMPLETED;
                case "canceled" -> CANCELLED;
                default -> throw new IllegalArgumentException("Unknown Stripe status: " + stripeStatus);
            };
        }
    }
}
