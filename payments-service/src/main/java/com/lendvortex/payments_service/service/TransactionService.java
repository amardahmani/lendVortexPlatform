package com.lendvortex.payments_service.service;

import com.lendvortex.payments_service.dto.TransactionRequestDTO;
import com.lendvortex.payments_service.dto.TransactionResponseDTO;

public interface TransactionService {
    TransactionResponseDTO saveTransaction(TransactionRequestDTO transactionRequestDTO);

    Iterable<TransactionResponseDTO> getTransactions();

    Iterable<TransactionResponseDTO> getTransactions(Long userId);

    TransactionResponseDTO getTransactionById(Long id);

    Boolean deleteTransaction(Long id);
}
