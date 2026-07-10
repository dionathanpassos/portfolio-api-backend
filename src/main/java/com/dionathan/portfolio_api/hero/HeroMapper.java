package com.dionathan.portfolio_api.hero;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.hero.dto.HeroRequestDTO;
import com.dionathan.portfolio_api.hero.dto.HeroResponseDTO;
import com.dionathan.portfolio_api.hero.dto.HeroUpdateRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class HeroMapper {

    public Hero toEntity(HeroRequestDTO requestDTO, User user) {
        Hero hero = new Hero();

        hero.setName(requestDTO.name());
        hero.setPosition(requestDTO.position());
        hero.setEyebrow(requestDTO.eyebrow());
        hero.setIntrodution(requestDTO.introdution());
        hero.setUser(user);

        return hero;
    }

    public HeroResponseDTO fromEntity(Hero hero) {
        return new HeroResponseDTO(
                hero.getId(),
                hero.getName(),
                hero.getPosition(),
                hero.getEyebrow(),
                hero.getIntrodution()
        );
    }

    public Hero updateToEntity(HeroUpdateRequestDTO requestDTO, Hero hero) {

        if(requestDTO.name() != null) {
            hero.setName(requestDTO.name());
        }
        if(requestDTO.position() != null) {
            hero.setPosition(requestDTO.position());
        }
        if(requestDTO.eyebrow() != null) {
            hero.setEyebrow(requestDTO.eyebrow());
        }
        if(requestDTO.introdution() != null) {
            hero.setIntrodution(requestDTO.introdution());
        }

        return hero;
    }
}
