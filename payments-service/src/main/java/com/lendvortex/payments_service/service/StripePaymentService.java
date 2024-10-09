package com.lendvortex.payments_service.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.math.BigDecimal;

public interface StripePaymentService {
    PaymentIntent createPaymentIntent(BigDecimal amount, String description) throws StripeException;
    PaymentIntent confirmPaymentIntent(String paymentIntentId) throws StripeException;

}
