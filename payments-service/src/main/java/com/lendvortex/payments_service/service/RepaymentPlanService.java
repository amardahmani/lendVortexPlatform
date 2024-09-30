package com.lendvortex.payments_service.service;


import com.lendvortex.payments_service.dto.RepaymentPlanRequestDTO;
import com.lendvortex.payments_service.dto.RepaymentPlanResponseDTO;

import java.util.List;

public interface RepaymentPlanService {

    RepaymentPlanResponseDTO createRepaymentPlan(RepaymentPlanRequestDTO repaymentPlanRequestDTO);
    List<RepaymentPlanResponseDTO> getRepaymentPlansByBorrowerId(Long borrowerId);

    void deactivateRepaymentPlan(Long repaymentPlanId);
    void triggerNextRepayment(Long repaymentPlanId);


}
