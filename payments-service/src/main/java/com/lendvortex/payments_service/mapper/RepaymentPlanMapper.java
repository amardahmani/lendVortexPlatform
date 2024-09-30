package com.lendvortex.payments_service.mapper;

import com.lendvortex.payments_service.dto.RepaymentPlanRequestDTO;
import com.lendvortex.payments_service.dto.RepaymentPlanResponseDTO;
import com.lendvortex.payments_service.entity.RepaymentPlan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RepaymentPlanMapper {

    RepaymentPlanMapper INSTANCE = Mappers.getMapper(RepaymentPlanMapper.class);

    RepaymentPlan toEntity(RepaymentPlanRequestDTO repaymentPlanRequestDTO);

    RepaymentPlanResponseDTO toResponse(RepaymentPlan repaymentPlan);

}
