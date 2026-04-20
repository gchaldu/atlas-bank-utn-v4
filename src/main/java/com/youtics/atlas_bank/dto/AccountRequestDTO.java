package com.youtics.atlas_bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
    @NotBlank(message = "El titular de la cuenta no puede estar vacío")
    private String holder;
    @NotNull(message = "El dato no puede ser nulo")
    @PositiveOrZero(message = "El saldo de la cuenta no puede ser cero ni negativo")
    private Double balance;
}
