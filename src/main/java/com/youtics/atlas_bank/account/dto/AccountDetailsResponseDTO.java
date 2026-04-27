package com.youtics.atlas_bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsResponseDTO {
    private Long id;
    private String address;
    private String phone;
    private String cuit;
}
