package com.lendvortex.loanservice.service.impl;

import com.lendvortex.loanservice.dto.request.DirectLoanRequestDTO;
import com.lendvortex.loanservice.dto.request.MarketplaceLoanApplicationRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanApplicationResponseDTO;
import com.lendvortex.loanservice.entity.LoanApplication;
import com.lendvortex.loanservice.mapper.LoanApplicationMapper;
import com.lendvortex.loanservice.repository.LoanApplicationRepository;
import com.lendvortex.loanservice.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanApplicationMapper loanApplicationMapper;

    @Override
    public LoanApplicationResponseDTO applyForLoanOffer(MarketplaceLoanApplicationRequestDTO marketplaceLoanApplicationRequestDTO) {
        LoanApplication loanApplication = loanApplicationMapper.toEntity(marketplaceLoanApplicationRequestDTO);

        loanApplication.setApplicationType(LoanApplication.ApplicationType.MARKETPLACE);
        loanApplication.setApplicationDate(new Timestamp(System.currentTimeMillis()));

        loanApplicationRepository.save(loanApplication);

        return loanApplicationMapper.toDTO(loanApplication);
    }

    @Override
    public LoanApplicationResponseDTO requestLoan(DirectLoanRequestDTO directLoanRequestDTO) {
        LoanApplication loanApplication = loanApplicationMapper.toEntity(directLoanRequestDTO);

        loanApplication.setApplicationDate(new Timestamp(System.currentTimeMillis()));
        loanApplication.setApplicationType(LoanApplication.ApplicationType.DIRECT);

        loanApplicationRepository.save(loanApplication);

        return loanApplicationMapper.toDTO(loanApplication);

    }

    @Override
    public Iterable<LoanApplicationResponseDTO> getApplicationsByType(String applicationType) {
        return loanApplicationRepository.getApplicationsByType(LoanApplication.ApplicationType.valueOf(applicationType)).stream()
                .map(loanApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanApplicationResponseDTO reviewLoanApplication(Long id, String status) {
        LoanApplication loanApplication = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan Application not found with ID: " + id));

        loanApplication.setStatus(LoanApplication.ApplicationStatus.valueOf(status));
        loanApplication.setDecisionDate(new Timestamp(System.currentTimeMillis()));

        loanApplicationRepository.save(loanApplication);
        return loanApplicationMapper.toDTO(loanApplication);
    }

    @Override
    public LoanApplicationResponseDTO getLoanApplicationById(Long id) {
        LoanApplication loanApplication = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan Application not found with ID: " + id));

        return loanApplicationMapper.toDTO(loanApplication);
    }
}
