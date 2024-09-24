package com.lendvortex.loanservice.repository;

import com.lendvortex.loanservice.entities.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {
    List<LoanApplication> getApplicationsByType(LoanApplication.ApplicationType applicationType);
}
