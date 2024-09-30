package com.lendvortex.payments_service.service;

import com.lendvortex.payments_service.dto.StripePaymentRequestDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface StripePaymentService {
    PaymentIntent createPayment(StripePaymentRequestDTO stripePaymentRequestDTO) throws StripeException;

    PaymentIntent getPaymentIntentStatus(String paymentIntentId) throws StripeException;
}
