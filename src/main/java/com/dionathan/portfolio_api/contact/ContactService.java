package com.dionathan.portfolio_api.contact;

import com.dionathan.portfolio_api.infra.email.ContactEmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactEmailService contactEmailService;

    public void sendContact(ContactRequestDTO requestDTO) {
        contactEmailService.sendConfirmationEmail(requestDTO.name(), requestDTO.email());
        contactEmailService.sendOwnerNotification(requestDTO.name(), requestDTO.email(), requestDTO.message());
    }
}
