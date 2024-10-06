package com.lendvortex.payments_service.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDTO {
    private String cardId;
    private Long userId;
    private String last4;
    private int expirationMonth;
    private int expirationYear;
    private String brand;
    private String country;
    private String stripeAccountId;
}
