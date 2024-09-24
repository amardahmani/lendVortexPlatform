package com.lendvortex.loanservice.mapper;

import com.lendvortex.loanservice.dto.request.DirectLoanRequestDTO;
import com.lendvortex.loanservice.dto.request.MarketplaceLoanApplicationRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanApplicationResponseDTO;
import com.lendvortex.loanservice.entities.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {
    LoanApplicationMapper INSTANCE = Mappers.getMapper(LoanApplicationMapper.class);

    LoanApplication toEntity(MarketplaceLoanApplicationRequestDTO marketplaceLoanApplicationRequestDTO);
    LoanApplication toEntity(DirectLoanRequestDTO directLoanRequestDTO);

    LoanApplicationResponseDTO toDTO(LoanApplication loanApplication);
}
