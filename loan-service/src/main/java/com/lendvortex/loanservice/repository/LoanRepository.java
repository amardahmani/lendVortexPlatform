package com.lendvortex.loanservice.repository;

import com.lendvortex.loanservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
