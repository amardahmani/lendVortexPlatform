package com.lendvortex.payments_service.service.impl;

import com.lendvortex.payments_service.dto.StripePaymentRequestDTO;
import com.lendvortex.payments_service.service.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodListParams;
import com.stripe.param.SetupIntentCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentServiceImpl implements StripePaymentService {

    private static final String STRIPE_ERROR_MESSAGE = "Error processing Stripe payment: ";

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

    @Override
    public SetupIntent createStripeInent(Long customerId) throws StripeException {
        SetupIntentCreateParams params = SetupIntentCreateParams.builder()
                .setCustomer(customerId.toString())
                .setUsage(SetupIntentCreateParams.Usage.OFF_SESSION)
                .build();

        return SetupIntent.create(params);
    }

    @Override
    public PaymentMethod getCustomerDefaultPaymentMethod(Long customerId) throws StripeException {
        Customer customer = Customer.retrieve(customerId.toString());
        String defaultPaymentMethodId = customer.getInvoiceSettings().getDefaultPaymentMethod();

        if(defaultPaymentMethodId != null){
            return PaymentMethod.retrieve(defaultPaymentMethodId);
        }

        PaymentMethodListParams params = PaymentMethodListParams.builder()
                .setCustomer(customerId.toString())
                .setType(PaymentMethodListParams.Type.CARD)
                .build();
        PaymentMethodCollection methods = PaymentMethod.list(params);

        if(!methods.getData().isEmpty()){
            return methods.getData().get(0);
        }
        return null;
    }


    @Override
    public PaymentIntent createAndConfirmPayment(Long customerId, Long amount, String currency) throws StripeException {
        PaymentMethod defaultPaymentMethod = getCustomerDefaultPaymentMethod(customerId);
        if (defaultPaymentMethod == null) {
            throw new RuntimeException("No default payment method found for customer");
        }

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency.toLowerCase())
                .setCustomer(customerId.toString())
                .setPaymentMethod(defaultPaymentMethod.getId())
                .setOffSession(true)
                .setConfirm(true)
                .build();

        try {
            return PaymentIntent.create(params);
        } catch (StripeException e) {
            throw new StripeException(STRIPE_ERROR_MESSAGE + e.getMessage(), e.getRequestId(), e.getCode(), e.getStatusCode()) {
            };
        }
    }

    @Override
    public Subscription createSubscription(String customerId, String priceId, String interval) throws StripeException {
        SubscriptionCreateParams.Item item =
                SubscriptionCreateParams.Item.builder()
                        .setPrice(priceId)
                        .build();

        SubscriptionCreateParams params = SubscriptionCreateParams.builder()
                .setCustomer(customerId)
                .addItem(item)
                .setBillingCycleAnchor(1L)
                .build();

        return Subscription.create(params);
    }
}
