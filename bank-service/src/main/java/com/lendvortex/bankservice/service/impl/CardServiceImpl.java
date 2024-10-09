package com.lendvortex.bankservice.service.impl;

import com.lendvortex.bankservice.dto.CardRequestDTO;
import com.lendvortex.bankservice.dto.CardResponseDTO;
import com.lendvortex.bankservice.entity.Card;
import com.lendvortex.bankservice.mapper.CardMapper;
import com.lendvortex.bankservice.repository.CardRepository;
import com.lendvortex.bankservice.service.CardService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public CardResponseDTO saveCard(CardRequestDTO cardRequestDTO) {
        Card card = cardMapper.toEntity(cardRequestDTO);

        cardRepository.save(card);

        return cardMapper.toResponseDTO(card);
    }

    @Override
    public List<CardResponseDTO> getCards(Long userId) {
        return cardRepository.getCardsByUserId(userId).stream().map(
                cardMapper::toResponseDTO
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<CardResponseDTO> getCardById(UUID cardId) {
        return Optional.ofNullable(cardRepository.findById(cardId)
                .map(CardMapper.INSTANCE::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found")));
    }

    @Override
    public Boolean deleteCard(UUID cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent()){
            cardRepository.deleteById(cardId);
            return true;
        }

        return false;

    }

    private PaymentMethod createPaymentMethod(Card card) throws StripeException {
        PaymentMethodCreateParams params = PaymentMethodCreateParams.builder()
                .setType(PaymentMethodCreateParams.Type.CARD)
                .setCard(PaymentMethodCreateParams.CardDetails.builder()
                        .setNumber(card.getCardNumber())
                        .setExpMonth((long) card.getExpirationMonth())
                        .setExpYear((long) card.getExpirationYear())
                        .setCvc(card.getLast4())
                        .build())
                .build();
        return PaymentMethod.create(params);
    }

    private PaymentMethod attachPaymentMethod(String paymentMethodId,String customerId) throws StripeException {
        PaymentMethodAttachParams attachParams = PaymentMethodAttachParams.builder()
                .setCustomer(customerId)
                .build();

        PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);
        return paymentMethod.attach(attachParams);
    }
}
