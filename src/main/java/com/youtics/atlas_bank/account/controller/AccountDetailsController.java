package com.youtics.atlas_bank.account.controller;

import com.youtics.atlas_bank.account.model.Account;
import com.youtics.atlas_bank.account.model.AccountDetails;
import com.youtics.atlas_bank.account.repository.AccountDetailsRepository;
import com.youtics.atlas_bank.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/account-details")
@RequiredArgsConstructor
public class AccountDetailsController {

    private final AccountRepository accountRepository;
    private final AccountDetailsRepository accountDetailsRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        Long accountId = Long.valueOf(body.get("accountId").toString());

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        AccountDetails details = new AccountDetails();
        details.setAddress((String) body.get("address"));
        details.setPhone((String) body.get("phone"));
        details.setCuit((String) body.get("cuit"));
        details.setAccount(account);
        account.setDetails(details);

        accountDetailsRepository.save(details);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "id", details.getId(),
                "address", details.getAddress(),
                "phone", details.getPhone(),
                "cuit", details.getCuit(),
                "accountId", accountId
        ));
    }
}
