package com.lendvortex.bankservice.service;

import com.lendvortex.bankservice.dto.CardRequestDTO;
import com.lendvortex.bankservice.dto.CardResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardService {

    CardResponseDTO saveCard(CardRequestDTO cardRequestDTO);

    List<CardResponseDTO> getCards(Long userId);

    Optional<CardResponseDTO> getCardById(UUID cardId);

    Boolean deleteCard(UUID cardId);
}
