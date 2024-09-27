package com.lendvortex.payments_service.mapper;

import com.lendvortex.payments_service.dto.TransactionRequestDTO;
import com.lendvortex.payments_service.dto.TransactionResponseDTO;
import com.lendvortex.payments_service.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toEntity(TransactionRequestDTO transactionRequestDTO);

    TransactionResponseDTO toResponseDTO(Transaction transaction);

}
