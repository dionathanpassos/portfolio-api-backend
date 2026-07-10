package com.dionathan.portfolio_api.hero;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import com.dionathan.portfolio_api.hero.dto.HeroRequestDTO;
import com.dionathan.portfolio_api.hero.dto.HeroResponseDTO;
import com.dionathan.portfolio_api.hero.dto.HeroUpdateRequestDTO;
import com.dionathan.portfolio_api.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;
    private final AuthenticatedUserService authenticatedUserService;

    @Transactional
    public HeroResponseDTO create(HeroRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Hero hero = heroMapper.toEntity(requestDTO, user);
        Hero saved = heroRepository.save(hero);

        return heroMapper.fromEntity(saved);
    }

    @Transactional
    public HeroResponseDTO update(HeroUpdateRequestDTO requestDTO, Long id){
        Hero hero = heroRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag não encontrada"));

        hero = heroMapper.updateToEntity(requestDTO, hero);

        Hero saved = heroRepository.save(hero);

        return heroMapper.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public HeroResponseDTO findByUser() {
        User user = authenticatedUserService.getAuthenticatedUser();

        Hero hero = heroRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Tag não encontrada"));

        return heroMapper.fromEntity(hero);
    }
}
