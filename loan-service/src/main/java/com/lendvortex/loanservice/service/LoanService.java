package com.lendvortex.loanservice.service;


import com.lendvortex.loanservice.dto.request.LoanRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanResponseDTO;

public interface LoanService {

    LoanResponseDTO saveLoan(LoanRequestDTO loanRequestDTO);

    Iterable<LoanResponseDTO> getAllLoans();

    LoanResponseDTO getLoanById(Long id);



}
