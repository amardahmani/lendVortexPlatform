package com.lendvortex.payments_service.service;

import com.lendvortex.payments_service.dto.request.LoanDisbursementRequest;
import com.lendvortex.payments_service.dto.response.DisbursementResponse;
import com.lendvortex.payments_service.dto.response.PaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.math.BigDecimal;

public interface StripePaymentService {
    PaymentIntent createPayment(Long userId, BigDecimal amount,String currency) throws StripeException;
    void syncCardWithStripe();

    PaymentResponse processPyament(String cardId,BigDecimal amount) throws StripeException;
    String createNewStripeCustomer(Long userId) throws StripeException;

    DisbursementResponse distributeLoan(LoanDisbursementRequest loanDisbursementRequest) throws StripeException;

}
