package com.dionathan.portfolio_api.about;

import com.dionathan.portfolio_api.about.dto.AboutRequestDTO;
import com.dionathan.portfolio_api.about.dto.AboutResponseDTO;
import com.dionathan.portfolio_api.about.dto.AboutUpdateRequestDTO;
import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import com.dionathan.portfolio_api.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.CacheResponse;

@Service
@RequiredArgsConstructor
public class AboutService {

    private final AboutRepository aboutRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final AboutMapper aboutMapper;

    @Transactional
    public AboutResponseDTO create(AboutRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        About about = aboutMapper.toEntity(requestDTO, user);
        About saved = aboutRepository.save(about);

        return aboutMapper.fromEntity(saved);
    }

    @Transactional
    public AboutResponseDTO update(AboutUpdateRequestDTO requestDTO, Long id) {
        About about = aboutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Informações não encontradas"));

        about = aboutMapper.updateToEntiy(requestDTO, about);

        About saved = aboutRepository.save(about);

        return aboutMapper.fromEntity(saved);
    }

    public AboutResponseDTO findByUser() {
        User user = authenticatedUserService.getAuthenticatedUser();

        About about = aboutRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Profile não encontrado"));

        return aboutMapper.fromEntity(about);
    }
}
