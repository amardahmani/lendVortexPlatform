package com.lendvortex.bankservice.service.impl;

import com.lendvortex.bankservice.dto.CardRequestDTO;
import com.lendvortex.bankservice.dto.CardResponseDTO;
import com.lendvortex.bankservice.entity.Card;
import com.lendvortex.bankservice.mapper.CardMapper;
import com.lendvortex.bankservice.repository.CardRepository;
import com.lendvortex.bankservice.service.CardService;
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
}
