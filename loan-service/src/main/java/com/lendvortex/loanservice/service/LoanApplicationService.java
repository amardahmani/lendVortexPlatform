package com.lendvortex.loanservice.service;

import com.lendvortex.loanservice.dto.request.DirectLoanRequestDTO;
import com.lendvortex.loanservice.dto.request.MarketplaceLoanApplicationRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanApplicationResponseDTO;

public interface LoanApplicationService {

    LoanApplicationResponseDTO applyForLoanOffer(MarketplaceLoanApplicationRequestDTO marketplaceLoanApplicationRequestDTO);

    LoanApplicationResponseDTO requestLoan(DirectLoanRequestDTO directLoanRequestDTO);

    Iterable<LoanApplicationResponseDTO> getApplicationsByType(String applicationType);

    LoanApplicationResponseDTO reviewLoanApplication(Long id,String status);

    LoanApplicationResponseDTO getLoanApplicationById(Long id);

}
