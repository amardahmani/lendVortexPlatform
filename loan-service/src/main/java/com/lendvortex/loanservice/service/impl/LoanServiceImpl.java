package com.lendvortex.loanservice.service.impl;

import com.lendvortex.loanservice.dto.request.LoanRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanResponseDTO;
import com.lendvortex.loanservice.entities.Loan;
import com.lendvortex.loanservice.mapper.LoanMapper;
import com.lendvortex.loanservice.repository.LoanRepository;
import com.lendvortex.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service

public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanMapper loanMapper;

    @Override
    public LoanResponseDTO saveLoan(LoanRequestDTO loanRequestDTO) {
        Loan loan = loanMapper.toEntity(loanRequestDTO);

        loanRepository.save(loan);

        return loanMapper.toResponse(loan);
    }

    @Override
    public Iterable<LoanResponseDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();

        return loans.stream()
                .map(loanMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoanResponseDTO getLoanById(Long id) {
        return loanRepository.findById(id).map(loanMapper::toResponse).
                orElseThrow(
                        () -> new NoSuchElementException("Element with id: "+ id+" not found")
                );
    }


}
