package com.youtics.atlas_bank.account.controller;

import com.youtics.atlas_bank.account.dto.AccountRequestDTO;
import com.youtics.atlas_bank.account.dto.AccountResponseDTO;

import com.youtics.atlas_bank.account.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAllAccounts(){
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> findAccountById(@PathVariable Long id){
        AccountResponseDTO responseDTO = accountService.findById(id).orElseThrow(
                () -> new RuntimeException("No existe la cuenta")
        );

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@Valid @RequestBody AccountRequestDTO account){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable Long id,@RequestBody AccountRequestDTO accountRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.update(id, accountRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
        accountService.delete(id);
        return ResponseEntity.ok("Account Id: " + id + "Eliminado");
    }
}
