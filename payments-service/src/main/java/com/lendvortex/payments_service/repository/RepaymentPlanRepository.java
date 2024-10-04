package com.lendvortex.payments_service.repository;

import com.lendvortex.payments_service.entity.RepaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepaymentPlanRepository extends JpaRepository<RepaymentPlan,Long> {
    List<RepaymentPlan> findByBorrowerId(Long borrowerId);

    @Query("select * from repayments where nextDueDate = Date.now()")
    List<RepaymentPlan> findReadyPayments();
}
