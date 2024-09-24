package com.lendvortex.loanservice.controller;

import com.lendvortex.loanservice.dto.request.LoanRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanResponseDTO;
import com.lendvortex.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans/")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public LoanResponseDTO createLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        return loanService.saveLoan(loanRequestDTO);
    }

    @GetMapping("/all")
    public Iterable<LoanResponseDTO> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public LoanResponseDTO getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

}
