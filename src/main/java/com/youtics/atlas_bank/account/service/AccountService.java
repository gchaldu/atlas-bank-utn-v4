package com.youtics.atlas_bank.account.service;

import com.youtics.atlas_bank.account.dto.AccountRequestDTO;
import com.youtics.atlas_bank.account.dto.AccountResponseDTO;
import com.youtics.atlas_bank.account.mapper.AccountMapper;
import com.youtics.atlas_bank.account.model.Account;
import com.youtics.atlas_bank.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAll() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::toResponse).toList();
    }

    @Override
    public AccountResponseDTO save(AccountRequestDTO accountRequestDTO) {
        Account account = AccountMapper.toEntity(accountRequestDTO);
        accountRepository.save(account);
        return AccountMapper.toResponse(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountResponseDTO> findById(Long id) {
        return accountRepository.findById(id).map(
                AccountMapper::toResponse
        );
    }

    @Override
    @Transactional
    public AccountResponseDTO update(Long id, AccountRequestDTO accountRequestDTO) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La cuenta no existe")
        );
        existingAccount.setBalance(accountRequestDTO.getBalance());
        existingAccount.setHolder(accountRequestDTO.getHolder());

        Account saved = accountRepository.save(existingAccount);

        return AccountMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
