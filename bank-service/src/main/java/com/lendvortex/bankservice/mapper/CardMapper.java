package com.lendvortex.bankservice.mapper;

import com.lendvortex.bankservice.dto.CardRequestDTO;
import com.lendvortex.bankservice.dto.CardResponseDTO;
import com.lendvortex.bankservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card toEntity(CardRequestDTO cardRequestDTO);

    CardResponseDTO toResponseDTO(Card card);
}
