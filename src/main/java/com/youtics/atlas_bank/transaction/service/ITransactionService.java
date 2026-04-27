package com.youtics.atlas_bank.transaction.service;

import com.youtics.atlas_bank.transaction.dto.TransactionRequestDTO;

public interface ITransactionService {
    void deposit(TransactionRequestDTO request);
}
