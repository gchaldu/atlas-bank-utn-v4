package com.youtics.atlas_bank.account.service;

import com.youtics.atlas_bank.account.dto.AccountRequestDTO;
import com.youtics.atlas_bank.account.dto.AccountResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<AccountResponseDTO> findAll();
    AccountResponseDTO save(AccountRequestDTO account);
    Optional<AccountResponseDTO> findById(Long id);
    AccountResponseDTO update(Long id, AccountRequestDTO accountRequestDTO);
    void delete(Long id);
}
