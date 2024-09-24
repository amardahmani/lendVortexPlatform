package com.lendvortex.loanservice.mapper;

import com.lendvortex.loanservice.dto.request.LoanRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanResponseDTO;
import com.lendvortex.loanservice.entities.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    Loan toEntity(LoanRequestDTO loanRequestDTO);

    LoanResponseDTO toResponse(Loan loan);
}
