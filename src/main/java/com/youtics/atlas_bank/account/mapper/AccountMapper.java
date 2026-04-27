package com.youtics.atlas_bank.account.mapper;

import com.youtics.atlas_bank.account.dto.AccountDetailsResponseDTO;
import com.youtics.atlas_bank.account.dto.AccountRequestDTO;
import com.youtics.atlas_bank.account.dto.AccountResponseDTO;
import com.youtics.atlas_bank.account.model.Account;
import com.youtics.atlas_bank.account.model.AccountDetails;
import com.youtics.atlas_bank.tag.dto.TagResponseDTO;
import com.youtics.atlas_bank.transaction.dto.TransactionResponseDTO;
import com.youtics.atlas_bank.transaction.model.Transaction;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountMapper {
    public static AccountResponseDTO toResponse(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setHolder(account.getHolder());
        dto.setBalance(account.getBalance());
//        List<TransactionResponseDTO> transactions = account.getTransactions()
//                .stream()
//                .map(transaction ->
//                        new TransactionResponseDTO(transaction.getId(), transaction.getAmount()))
//                .toList();
//        dto.setTransactions(transactions);
        // @OneToMany → Transactions
        // @OneToOne → AccountDetails
        AccountDetails details = account.getDetails();
        if (details != null) {
            dto.setDetails(new AccountDetailsResponseDTO(
                    details.getId(),
                    details.getAddress(),
                    details.getPhone(),
                    details.getCuit()
            ));
        }
        List<TransactionResponseDTO> transactions = account.getTransactions()
                .stream()
                .map(t -> new TransactionResponseDTO(
                        t.getId(),
                        t.getAmount(),
                        t.getType(),
                        t.getCreatedAt()
                ))
                .toList();
        dto.setTransactions(transactions);

        // @ManyToMany → Tags
        Set<TagResponseDTO> tags = account.getTags()
                .stream()
                .map(tag -> new TagResponseDTO(tag.getId(), tag.getName()))
                .collect(Collectors.toSet());
        dto.setTags(tags);

        return dto;
    }

    public static Account toEntity(AccountRequestDTO accountRequestDTO){
        Account account = new Account();
        account.setHolder(accountRequestDTO.getHolder());
        account.setBalance(accountRequestDTO.getBalance());
        return account;
    }
}
