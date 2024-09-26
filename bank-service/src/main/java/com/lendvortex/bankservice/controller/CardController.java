package com.lendvortex.bankservice.controller;


import com.lendvortex.bankservice.dto.CardRequestDTO;
import com.lendvortex.bankservice.dto.CardResponseDTO;
import com.lendvortex.bankservice.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponseDTO> saveCard(@Validated @RequestBody CardRequestDTO cardRequestDTO) {
        CardResponseDTO savedCard = cardService.saveCard(cardRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CardResponseDTO>> getCardsByUserId(@PathVariable Long userId) {
        List<CardResponseDTO> cards = cardService.getCards(userId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable UUID cardId) {
        CardResponseDTO card = cardService.getCardById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID cardId) {
        if (cardService.deleteCard(cardId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
