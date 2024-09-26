package com.lendvortex.loanservice.mapper;


import com.lendvortex.loanservice.dto.request.LoanOfferRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanOfferResponseDTO;
import com.lendvortex.loanservice.entity.LoanOffer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanOfferMapper {
    LoanOfferMapper INSTANCE = Mappers.getMapper(LoanOfferMapper.class);

    LoanOffer toEntity(LoanOfferRequestDTO loanOfferRequestDTO);

    LoanOfferResponseDTO toResponseDTO(LoanOffer loanOffer);
}
