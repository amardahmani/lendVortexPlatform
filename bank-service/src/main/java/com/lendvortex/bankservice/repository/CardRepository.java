package com.lendvortex.bankservice.repository;

import com.lendvortex.bankservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> getCardsByUserId(Long userId);
}
