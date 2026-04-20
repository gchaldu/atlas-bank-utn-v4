package com.youtics.atlas_bank.service;

import com.youtics.atlas_bank.dto.AccountRequestDTO;
import com.youtics.atlas_bank.dto.AccountResponseDTO;
import com.youtics.atlas_bank.mapper.AccountMapper;
import com.youtics.atlas_bank.model.Account;
import com.youtics.atlas_bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;

    @Override
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
    public Optional<AccountResponseDTO> findById(Long id) {
        return accountRepository.findById(id).map(
                AccountMapper::toResponse
        );
    }

    @Override
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
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
