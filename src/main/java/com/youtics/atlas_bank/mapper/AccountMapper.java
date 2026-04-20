package com.youtics.atlas_bank.mapper;

import com.youtics.atlas_bank.dto.AccountRequestDTO;
import com.youtics.atlas_bank.dto.AccountResponseDTO;
import com.youtics.atlas_bank.model.Account;

public class AccountMapper {
    public static AccountResponseDTO toResponse(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setHolder(account.getHolder());
        dto.setBalance(account.getBalance());
        // Agregá aquí el resto de los campos necesarios
        return dto;
    }

    public static Account toEntity(AccountRequestDTO accountRequestDTO){
        Account account = new Account();
        account.setHolder(accountRequestDTO.getHolder());
        account.setBalance(accountRequestDTO.getBalance());
        return account;
    }
}
