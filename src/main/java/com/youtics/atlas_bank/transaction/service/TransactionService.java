package com.youtics.atlas_bank.transaction.service;

import com.youtics.atlas_bank.account.model.Account;
import com.youtics.atlas_bank.account.repository.AccountRepository;
import com.youtics.atlas_bank.transaction.dto.TransactionRequestDTO;
import com.youtics.atlas_bank.transaction.emun.TransactionType;
import com.youtics.atlas_bank.transaction.model.Transaction;
import com.youtics.atlas_bank.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService{

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public void deposit(TransactionRequestDTO request) {

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        account.setBalance(account.getBalance().add(request.getAmount()));

        // Crear la transacción y vincularla (Relación Many-to-One)
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setAccount(account); // Establecemos la relación

        // Persistir los cambios
        // Al estar en una @Transactional, Hibernate detecta el cambio en 'account'
        // y hará el UPDATE automáticamente al terminar el método.
        transactionRepository.save(transaction);
    }
}
