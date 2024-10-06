package com.lendvortex.payments_service.service.impl;

import com.lendvortex.payments_service.dto.request.LoanDisbursementRequest;
import com.lendvortex.payments_service.dto.response.CardResponseDTO;
import com.lendvortex.payments_service.dto.response.DisbursementResponse;
import com.lendvortex.payments_service.dto.response.PaymentResponse;
import com.lendvortex.payments_service.service.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Transfer;
import com.stripe.param.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;

public class StripePaymentServiceImpl implements StripePaymentService {
    @Override
    public PaymentIntent createPayment(Long userId, BigDecimal amount, String currency) throws StripeException {
        String customerId = getOrCreateCustomer(userId);
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount.multiply(new BigDecimal("100")).longValue())
                .setCurrency(currency)
                .setCustomer(customerId)
                .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
                .build();

        return PaymentIntent.create(params);
    }

    @Override
    public void syncCardWithStripe() {

    }

    @Override
    public PaymentResponse processPyament(String cardId, BigDecimal amount) throws StripeException {
        return null;
    }

    @Override
    public String createNewStripeCustomer(Long userId) throws StripeException{

        CustomerCreateParams params = CustomerCreateParams.builder()
                .putMetadata("user_id", userId.toString())
                .build();

        Customer customer = Customer.create(params);

       //TODO: Maybe create the customer entity

        return customer.getId();

    }

    @Override
    public DisbursementResponse distributeLoan(LoanDisbursementRequest loanDisbursementRequest) throws StripeException {

        //TODO: client get the borrowerAccount
        CardResponseDTO card = new CardResponseDTO();

        TransferCreateParams transferCreateParams = TransferCreateParams.builder()
                .setAmount(loanDisbursementRequest.getAmount().multiply(new BigDecimal("100")).longValue())
                .setCurrency("usd")
                .setDestination(card.getStripeAccountId())
                .setTransferGroup(loanDisbursementRequest.getLoanId().toString())
                .putMetadata("loan_id", loanDisbursementRequest.getLoanId().toString())
                .putMetadata("borrower_id", loanDisbursementRequest.getBorrowerId().toString())
                .build();
        Transfer transfer = Transfer.create(transferCreateParams);

        //TODO: maybe create a transactionEntity and save into it
        //LoanDisbursement disbursement = createDisbursementRecord(request, transfer);
        //disbursementRepository.save(disbursement);

        DisbursementResponse disbursementResponse = new DisbursementResponse();


        disbursementResponse.setLoanId(UUID.fromString(transfer.getId()));
        disbursementResponse.setBankAccountLast4("1234");
        disbursementResponse.setRecipientName("AZE");
        disbursementResponse.setBorrowerId(1L);
        disbursementResponse.setSpecificStatus(DisbursementResponse.DisbursementStatus.COMPLETED);
        return disbursementResponse;
    }

    /*@Override
    @Transactional
    public PaymentResponse processPyament(String cardId, BigDecimal amount) throws StripeException {
        //TODO: implement the spring client for the bank-service
        CardResponseDTO card = new CardResponseDTO();
        card.setCardId("ddd");

        if (card == null) {
            throw new NoSuchElementException("card with cardId: "+cardId +" Not found");
        }

        Long customerId = Long.valueOf(getOrCreateCustomer(card.getUserId()));
        PaymentIntent paymentIntent = createPayment(
                customerId,
                amount,
                "USD"
        );

        //TODO: implement the paymentResponseService for this + add relational schema for it
        return new PaymentResponse(paymentIntent.getId(), PaymentResponse.PaymentStatus.SUCCEEDED);


    }
    */
    private String getOrCreateCustomer(Long userId) {
        return null;
    }
    /*
    @Override
    public void syncCardWithStripe() {
        PaymentMethodCreateParams params = PaymentMethodCreateParams.builder()
                .setType(PaymentMethodCreateParams.Type.CARD)
                .setCard(
                        PaymentMethodCreateParams.CardDetails.builder()
                                .setNumber(card.getCardNumber())
                                .setExpMonth(card.getExpirationMonth())
                                .setExpYear(card.getExpirationYear())
                                .setCvc(card.getCvc())
                                .build()
                )
                .build();

        PaymentMethod paymentMethod = PaymentMethod.create(params);
        paymentMethod.attach(PaymentMethodAttachParams.builder()
                .setCustomer(customerId)
                .build());
    }
    */


    //TODO: LOAN repayment but it is not working there are missing services& entities
    /*
    @Transactional
    public RepaymentResult processRepayment(RepaymentRequest request) {
        try {
            // Get card and payment method
            CardDTO card = bankServiceClient.getCard(request.getCardId());
            PaymentMethodEntity paymentMethod = paymentMethodRepository
                    .findByCardId(request.getCardId())
                    .orElseThrow(() -> new PaymentMethodNotFoundException("Payment method not found"));

            // Create payment intent
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(request.getAmount().multiply(new BigDecimal("100")).longValue())
                    .setCurrency("usd")
                    .setCustomer(getOrCreateStripeCustomer(request.getBorrowerId()))
                    .setPaymentMethod(paymentMethod.getStripePaymentMethodId())
                    .setConfirm(true)
                    .setOffSession(true)
                    .putMetadata("loan_id", request.getLoanId().toString())
                    .putMetadata("repayment_schedule_id", request.getScheduleId().toString())
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Record repayment
            LoanRepayment repayment = createRepaymentRecord(request, paymentIntent);
            repaymentRepository.save(repayment);

            return new RepaymentResult(
                    paymentIntent.getId(),
                    RepaymentStatus.fromStripeStatus(paymentIntent.getStatus()),
                    paymentIntent.getAmount()
            );

        } catch (StripeException e) {
            log.error("Failed to process repayment", e);
            throw new PaymentProcessingException("Repayment processing failed", e);
        }
    }
    */

    //TODO: Scheduling automatic repayments
    /*
    public void scheduleAutomaticRepayment(RepaymentScheduleRequest request) {
        // Create a scheduled task for automatic repayment
        RepaymentSchedule schedule = RepaymentSchedule.builder()
                .loanId(request.getLoanId())
                .borrowerId(request.getBorrowerId())
                .amount(request.getAmount())
                .scheduledDate(request.getScheduledDate())
                .status(ScheduleStatus.SCHEDULED)
                .build();

        scheduleRepository.save(schedule);
    }
    */
}
