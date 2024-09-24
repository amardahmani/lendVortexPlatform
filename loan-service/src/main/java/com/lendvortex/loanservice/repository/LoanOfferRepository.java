package com.lendvortex.loanservice.repository;

import com.lendvortex.loanservice.entities.LoanOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanOfferRepository extends JpaRepository<LoanOffer,Long> {
}
