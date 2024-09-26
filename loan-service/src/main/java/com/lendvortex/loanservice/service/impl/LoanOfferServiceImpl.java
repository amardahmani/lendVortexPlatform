package com.lendvortex.loanservice.service.impl;

import com.lendvortex.loanservice.dto.request.LoanOfferRequestDTO;
import com.lendvortex.loanservice.dto.response.LoanOfferResponseDTO;
import com.lendvortex.loanservice.entity.LoanOffer;
import com.lendvortex.loanservice.mapper.LoanOfferMapper;
import com.lendvortex.loanservice.repository.LoanOfferRepository;
import com.lendvortex.loanservice.service.LoanOfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanOfferServiceImpl implements LoanOfferService {

    @Autowired
    private LoanOfferRepository loanOfferRepository;

    @Autowired
    private LoanOfferMapper loanOfferMapper;

    @Override
    public LoanOfferResponseDTO saveLoanOffer(LoanOfferRequestDTO loanOfferRequestDTO) {
        LoanOffer loanOffer = loanOfferMapper.toEntity(loanOfferRequestDTO);

        loanOffer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        loanOffer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));


        loanOfferRepository.save(loanOffer);

        return loanOfferMapper.toResponseDTO(loanOffer);
    }

    @Override
    public Iterable<LoanOfferResponseDTO> getAllLoans() {
        List<LoanOffer> loanOffers = loanOfferRepository.findAll();

        List<LoanOfferResponseDTO> loanOfferResponseDTOS = loanOffers.stream()
                .map(loanOfferMapper::toResponseDTO)
                .collect(Collectors.toList());

        return loanOfferResponseDTOS;
    }

    @Override
    public boolean deleteLoanOffer(Long id) {
        Optional<LoanOffer> loanOffer = loanOfferRepository.findById(id);

        if(loanOffer.isPresent()){
            loanOfferRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }



    @Override
    public LoanOfferResponseDTO getLoanById(Long id) {
        return loanOfferRepository.findById(id).map(loanOfferMapper::toResponseDTO).orElseThrow(
                () -> new NoSuchElementException("loan offer with id: "+id+" not found")
        );
    }
}
