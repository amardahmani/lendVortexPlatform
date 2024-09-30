package com.lendvortex.payments_service.repository;

import com.lendvortex.payments_service.entity.RepaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentPlanRepository extends JpaRepository<RepaymentPlan,Long> {
    List<RepaymentPlan> findByBorrowerId(Long borrowerId);
}
