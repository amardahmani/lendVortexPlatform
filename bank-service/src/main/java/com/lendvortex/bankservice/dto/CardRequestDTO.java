package com.lendvortex.bankservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CardRequestDTO {

    private Long userId;
    private String last4;
    private int expirationMonth;
    private int expirationYear;
    private String brand;
    private String country;
}
