package com.dionathan.portfolio_api.social;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import com.dionathan.portfolio_api.security.AuthenticatedUserService;
import com.dionathan.portfolio_api.social.dto.SocialRequestDTO;
import com.dionathan.portfolio_api.social.dto.SocialResponseDTO;
import com.dionathan.portfolio_api.social.dto.SocialUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final AuthenticatedUserService authenticatedUserService;
    private final SocialRepository socialRepository;
    private final SocialMapper socialMapper;

    @Transactional
    public SocialResponseDTO create(SocialRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Social social = socialMapper.toEntity(requestDTO, user);
        Social saved = socialRepository.save(social);

        return socialMapper.fromEntity(saved);
    }



    @Transactional(readOnly = true)
    public SocialResponseDTO findByUser() {
        User user = authenticatedUserService.getAuthenticatedUser();

        Social social = socialRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Contatos não encontrado."));

        return socialMapper.fromEntity(social);
    }

    @Transactional
    public SocialResponseDTO update(SocialUpdateRequestDTO requestDTO, Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Social social = socialRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado."));

        social = socialMapper.updateToEntity(requestDTO, social);

        return socialMapper.fromEntity(social);
    }
}
