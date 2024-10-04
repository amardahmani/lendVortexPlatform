package com.lendvortex.payments_service.controller;

import com.lendvortex.payments_service.service.StripePaymentService;
import com.lendvortex.payments_service.service.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StripePaymentService stripePaymentService;

    @PostMapping("/disbure-loan")
    public ResponseEntity<> disburseLoan(){
        return null;
    }
}
