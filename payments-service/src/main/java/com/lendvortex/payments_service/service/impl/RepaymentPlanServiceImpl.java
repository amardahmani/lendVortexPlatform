package com.lendvortex.payments_service.service.impl;

import com.lendvortex.payments_service.dto.RepaymentPlanRequestDTO;
import com.lendvortex.payments_service.dto.RepaymentPlanResponseDTO;
import com.lendvortex.payments_service.entity.RepaymentPlan;
import com.lendvortex.payments_service.mapper.RepaymentPlanMapper;
import com.lendvortex.payments_service.repository.RepaymentPlanRepository;
import com.lendvortex.payments_service.service.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepaymentPlanServiceImpl implements RepaymentPlanService {

    @Autowired
    private RepaymentPlanRepository repaymentPlanRepository;

    @Autowired
    private RepaymentPlanMapper repaymentPlanMapper;

    @Override
    public RepaymentPlanResponseDTO createRepaymentPlan(RepaymentPlanRequestDTO repaymentPlanRequestDTO) {
        RepaymentPlan repaymentPlan = repaymentPlanMapper.toEntity(repaymentPlanRequestDTO);
        repaymentPlan.calculateNextDueDate();
        repaymentPlanRepository.save(repaymentPlan);

        return repaymentPlanMapper.toResponse(repaymentPlan);
    }

    @Override
    public List<RepaymentPlanResponseDTO> getRepaymentPlansByBorrowerId(Long borrowerId) {
        List<RepaymentPlan> plans = repaymentPlanRepository.findByBorrowerId(borrowerId);
        return plans.stream()
                .map(repaymentPlanMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateRepaymentPlan(Long repaymentPlanId) {
        RepaymentPlan plan = repaymentPlanRepository.findById(repaymentPlanId)
                .orElseThrow(() -> new RuntimeException("Repayment plan not found"));
        plan.setActive(false);
        repaymentPlanRepository.save(plan);
    }

    @Override

    public void triggerNextRepayment(Long repaymentPlanId) {

    }
}
