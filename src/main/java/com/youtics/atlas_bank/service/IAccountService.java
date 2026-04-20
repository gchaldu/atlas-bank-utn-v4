package com.youtics.atlas_bank.service;

import com.youtics.atlas_bank.dto.AccountRequestDTO;
import com.youtics.atlas_bank.dto.AccountResponseDTO;
import com.youtics.atlas_bank.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<AccountResponseDTO> findAll();
    AccountResponseDTO save(AccountRequestDTO account);
    Optional<AccountResponseDTO> findById(Long id);
    AccountResponseDTO update(Long id, AccountRequestDTO accountRequestDTO);
    void delete(Long id);
}
