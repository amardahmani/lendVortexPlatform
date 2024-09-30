package com.lendvortex.payments_service.service.impl;

import com.lendvortex.payments_service.dto.StripePaymentRequestDTO;
import com.lendvortex.payments_service.service.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentServiceImpl implements StripePaymentService {
    @Override
    public PaymentIntent createPayment(StripePaymentRequestDTO stripePaymentRequestDTO) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(stripePaymentRequestDTO.getAmount())
                .setCurrency(stripePaymentRequestDTO.getCurrency())
                .setPaymentMethod(stripePaymentRequestDTO.getPaymentMethodType())
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .setConfirm(true)
                .build();

        return PaymentIntent.create(params);
    }

    @Override
    public PaymentIntent getPaymentIntentStatus(String paymentIntentId) throws StripeException {
        return PaymentIntent.retrieve(paymentIntentId);
    }
}
