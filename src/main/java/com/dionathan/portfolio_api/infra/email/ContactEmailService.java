package com.dionathan.portfolio_api.infra.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactEmailService {

    @Value("${spring.mail.username}")
    private String ownerEmail;

    private final EmailService emailService;

    public void sendConfirmationEmail(String name, String email) {
        EmailMessage message = new EmailMessage(
                email,
                "Recebemos sua mensagem",
                """
                      Olá, %s! Tudo bem?
                      
                      Obrigado por entrar em contato.
                      Estarei analisando sua mensagem e entrarei em contato o mais breve possível.
                      
                      
                      Best regards.                        
                      """.formatted(name)
        );

        emailService.send(message);
    }

    public void sendOwnerNotification(String name, String visitorEmail, String contactMessage) {
        EmailMessage message = new EmailMessage(
                ownerEmail,
                "Novo contato via Portfólio",
                """
                        Nome: %s
                        Email: %s
                        
                        Mensagem:
                        %s  
                                                    
                """.stripIndent().formatted(name, visitorEmail, contactMessage)
        );

        emailService.send(message);
    }
}
