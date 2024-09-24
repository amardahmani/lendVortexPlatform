package com.lendvortex.loanservice.service;

import com.lendvortex.loanservice.dto.request.LoanOfferRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanOfferResponseDTO;

public interface LoanOfferService {

    LoanOfferResponseDTO saveLoanOffer(LoanOfferRequestDTO loanOfferRequestDTO);

    Iterable<LoanOfferResponseDTO> getAllLoans();

    boolean deleteLoanOffer(Long id);


    LoanOfferResponseDTO getLoanById(Long id);
}
