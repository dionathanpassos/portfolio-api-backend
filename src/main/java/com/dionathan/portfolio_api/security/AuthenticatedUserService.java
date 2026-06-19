package com.dionathan.portfolio_api.security;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.auth.UserRepository;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new AuthenticationCredentialsNotFoundException("Usuário não autenticado");
        }

        User user = (User) authentication.getPrincipal();
        return userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }
}
