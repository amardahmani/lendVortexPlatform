package com.lendvortex.payments_service.service;

import com.lendvortex.payments_service.dto.StripePaymentRequestDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.SetupIntent;
import com.stripe.model.Subscription;

public interface StripePaymentService {
    PaymentIntent createPayment(StripePaymentRequestDTO stripePaymentRequestDTO) throws StripeException;

    PaymentIntent getPaymentIntentStatus(String paymentIntentId) throws StripeException;

    SetupIntent createStripeInent (Long customerId) throws StripeException;

    PaymentMethod getCustomerDefaultPaymentMethod(Long customerId) throws StripeException;

    PaymentIntent createAndConfirmPayment(Long customerId, Long amount, String currency) throws StripeException;

    Subscription createSubscription(String customerId, String priceId, String interval) throws StripeException;
}
