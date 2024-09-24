package com.lendvortex.loanservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketplaceLoanApplicationRequestDTO {

    private Long borrowerId;
    private Long loanOfferId;
    private String purpose;

    private int termMonths;

}