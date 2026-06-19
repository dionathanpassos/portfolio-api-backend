package com.dionathan.portfolio_api.skills;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.skills.dto.SkillRequestDTO;
import com.dionathan.portfolio_api.skills.dto.SkillResponseDTO;
import com.dionathan.portfolio_api.skills.dto.SkillUpdateRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillMapper {

    public Skill toEntity(SkillRequestDTO requestDTO, User user) {
        Skill skill = new Skill();

        skill.setName(requestDTO.name().trim());
        skill.setCategory(requestDTO.category());
        skill.setIconUrl(requestDTO.iconUrl());
        skill.setLevel(requestDTO.level());
        skill.setUser(user);

        return skill;
    }

    public SkillResponseDTO fromEntity(Skill skill) {
        return new SkillResponseDTO(
                skill.getId(),
                skill.getName(),
                skill.getCategory(),
                skill.getIconUrl(),
                skill.getLevel(),
                skill.isActive()
        );
    }

    public List<SkillResponseDTO> fromEntity(List<Skill> skills) {
        return skills.stream().map(this::fromEntity).toList();
    }

    public Skill updateToEntity(SkillUpdateRequestDTO requestDTO, Skill skill) {

        if(requestDTO.name() != null) {
            skill.setName(requestDTO.name());
        }
        if(requestDTO.category() != null) {
            skill.setCategory(requestDTO.category());
        }
        if(requestDTO.iconUrl() != null) {
            skill.setIconUrl(requestDTO.iconUrl());
        }
        if(requestDTO.level() != null) {
            skill.setLevel(requestDTO.level());
        }

        return skill;

    }
}
