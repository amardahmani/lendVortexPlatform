package com.lendvortex.loanservice.controller;


import com.lendvortex.loanservice.dto.request.DirectLoanRequestDTO;
import com.lendvortex.loanservice.dto.request.MarketplaceLoanApplicationRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanApplicationResponseDTO;
import com.lendvortex.loanservice.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-applications/")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping("/apply-for-loan-offer")
    public LoanApplicationResponseDTO applyForLoanOffer(@RequestBody MarketplaceLoanApplicationRequestDTO requestDTO) {
        return loanApplicationService.applyForLoanOffer(requestDTO);
    }

    @PostMapping("/request-loan")
    public LoanApplicationResponseDTO requestLoan(@RequestBody DirectLoanRequestDTO requestDTO) {
        return loanApplicationService.requestLoan(requestDTO);
    }

    @GetMapping("/applications-by-type/{applicationType}")
    public List<LoanApplicationResponseDTO> getApplicationsByType(@PathVariable String applicationType) {
        return (List<LoanApplicationResponseDTO>) loanApplicationService.getApplicationsByType(applicationType);
    }

    @PutMapping("/review-loan-application/{id}")
    public LoanApplicationResponseDTO reviewLoanApplication(@PathVariable Long id, @RequestParam String status) {
        return loanApplicationService.reviewLoanApplication(id, status);
    }

    @GetMapping("/{id}")
    public LoanApplicationResponseDTO getLoanApplicationById(@PathVariable Long id) {
        return loanApplicationService.getLoanApplicationById(id);
    }


}
