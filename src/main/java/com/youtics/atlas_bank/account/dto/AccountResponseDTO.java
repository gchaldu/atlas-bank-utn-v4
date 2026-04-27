package com.youtics.atlas_bank.account.dto;

import com.youtics.atlas_bank.tag.dto.TagResponseDTO;
import com.youtics.atlas_bank.transaction.dto.TransactionResponseDTO;
import com.youtics.atlas_bank.transaction.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private String holder;
    private BigDecimal balance;
    private AccountDetailsResponseDTO details;
    private List<TransactionResponseDTO> transactions;
    private Set<TagResponseDTO> tags;
}
