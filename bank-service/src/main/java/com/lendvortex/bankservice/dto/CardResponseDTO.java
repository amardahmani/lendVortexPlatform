package com.lendvortex.bankservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CardResponseDTO {
    private UUID cardId;
    private Long userId;
    private String last4;
    private int expirationMonth;
    private int expirationYear;
    private String brand;
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
