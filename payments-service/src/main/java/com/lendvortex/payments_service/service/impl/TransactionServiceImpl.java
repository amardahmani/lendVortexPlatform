package com.lendvortex.payments_service.service.impl;

import com.lendvortex.payments_service.dto.TransactionRequestDTO;
import com.lendvortex.payments_service.dto.TransactionResponseDTO;
import com.lendvortex.payments_service.entity.Transaction;
import com.lendvortex.payments_service.mapper.TransactionMapper;
import com.lendvortex.payments_service.repository.TransactionRepository;
import com.lendvortex.payments_service.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionResponseDTO saveTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionRequestDTO);
        transactionRepository.save(transaction);

        return transactionMapper.toResponseDTO(transaction);

    }

    @Override
    public Iterable<TransactionResponseDTO> getTransactions() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Iterable<TransactionResponseDTO> getTransactions(Long userId) {
        return null;
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        return transaction.map(transactionMapper::toResponseDTO)
                .orElseThrow(() ->
                        new EntityNotFoundException("the transaction with id: "+ id +" not found")
                );

    }

    @Override
    public Boolean deleteTransaction(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        if(transaction.isPresent()){
            transactionRepository.deleteById(id);
            return true;
        }

        return false;
    }


}
