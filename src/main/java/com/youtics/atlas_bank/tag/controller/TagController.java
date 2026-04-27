package com.youtics.atlas_bank.tag.controller;

import com.youtics.atlas_bank.account.model.Account;
import com.youtics.atlas_bank.account.repository.AccountRepository;
import com.youtics.atlas_bank.tag.model.Tag;
import com.youtics.atlas_bank.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepository;
    private final AccountRepository accountRepository;

    // Crear etiqueta
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> body) {
        Tag tag = new Tag();
        tag.setName(body.get("name"));
        tagRepository.save(tag);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "id", tag.getId(),
                "name", tag.getName()
        ));
    }

    // Asociar etiqueta a cuenta
    @PostMapping("/{tagId}/accounts/{accountId}")
    @Transactional
    public ResponseEntity<?> assignToAccount(@PathVariable Long tagId,
                                             @PathVariable Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag no encontrado"));

        account.getTags().add(tag);

        return ResponseEntity.ok(Map.of(
                "message", "Tag '" + tag.getName() + "' asociado a cuenta " + accountId
        ));
    }
}
