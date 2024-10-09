package com.lendvortex.payments_service.service.impl;

import com.lendvortex.payments_service.service.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StripePaymentServiceImpl {

}
