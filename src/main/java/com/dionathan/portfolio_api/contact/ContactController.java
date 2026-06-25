package com.dionathan.portfolio_api.contact;

import com.dionathan.portfolio_api.infra.email.ContactEmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Void> sendContact(@Valid @RequestBody ContactRequestDTO requestDTO) {
        contactService.sendContact(requestDTO);

        return ResponseEntity.noContent().build();
    }
}
