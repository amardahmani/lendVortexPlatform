package com.lendvortex.payments_service.scheduler;

import com.lendvortex.payments_service.entity.RepaymentPlan;
import com.lendvortex.payments_service.repository.RepaymentPlanRepository;
import com.lendvortex.payments_service.service.RepaymentPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepaymentScheduler {

    private static final Logger logger= LoggerFactory.getLogger(RepaymentScheduler.class);

    @Autowired
    private RepaymentPlanService repaymentPlanService;

    @Autowired
    private RepaymentPlanRepository repaymentPlanRepository;

    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleRepayments(){
        logger.info("started scheduled repayment processing");
        List<RepaymentPlan> activePlans = repaymentPlanRepository.findReadyPayments();

        logger.info("active plans {} ",activePlans);

        for (RepaymentPlan plan : activePlans) {
            try {
                repaymentPlanService.triggerNextRepayment(plan.getRepaymentId());
                logger.info("Successfully processed repayment for plan ID: {}", plan.getRepaymentId());
            } catch (Exception e) {
                logger.error("Error processing repayment for plan ID: {}", plan.getRepaymentId(), e);
            }
        }
        logger.info("Completed scheduled repayment processing");
    }
}
