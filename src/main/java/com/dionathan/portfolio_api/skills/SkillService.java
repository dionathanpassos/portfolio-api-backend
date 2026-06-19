package com.dionathan.portfolio_api.skills;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.exception.BusinessException;
import com.dionathan.portfolio_api.exception.ConflictException;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import com.dionathan.portfolio_api.security.AuthenticatedUserService;
import com.dionathan.portfolio_api.skills.dto.SkillRequestDTO;
import com.dionathan.portfolio_api.skills.dto.SkillResponseDTO;
import com.dionathan.portfolio_api.skills.dto.SkillUpdateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final AuthenticatedUserService authenticatedUserService;
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillResponseDTO create(SkillRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        if(skillRepository.existsByNameAndUser(requestDTO.name().trim(), user)) {
            throw new ConflictException("Stack já cadastrada");
        }

        Skill skill = skillMapper.toEntity(requestDTO, user);
        Skill saved = skillRepository.save(skill);

        return skillMapper.fromEntity(saved);
    }

    public SkillResponseDTO findById(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Skill skill = skillRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Skill não encontrada"));

        return skillMapper.fromEntity(skill);
    }

    @Transactional(readOnly = true)
    public Page<SkillResponseDTO> findAll(Pageable pageable) {
        User user = authenticatedUserService.getAuthenticatedUser();

        return skillRepository.findAllByUserAndDeletedIsFalse(pageable, user).map(skillMapper::fromEntity);
    }

    @Transactional
    public SkillResponseDTO deactivate(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill não encontrada"));

        if(!skill.isActive()) {
            throw new BusinessException("Skill já desativada");
        }

        skill.setActive(false);

        return skillMapper.fromEntity(skill);
    }

    @Transactional
    public SkillResponseDTO activate(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill não encontrada"));

        if(skill.isActive()) {
            throw new BusinessException("Skill já ativa");
        }

        skill.setActive(true);

        return skillMapper.fromEntity(skill);
    }

    @Transactional
    public SkillResponseDTO delete(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Skill skill = skillRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Skill não encontrada"));

        if(skill.isDeleted()) {
            throw new BusinessException("Skill já excluída");
        }

        skill.setDeleted(true);

        return skillMapper.fromEntity(skill);
    }

    @Transactional
    public SkillResponseDTO update(Long id, SkillUpdateRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Skill skill = skillRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Skill não encontrada"));

        if(skill.isDeleted() || !skill.isActive()) {
            throw new BusinessException("Skill excluída/desavitada, não é possível alterar");
        }

        skill = skillMapper.updateToEntity(requestDTO, skill);
        Skill saved = skillRepository.save(skill);

        return skillMapper.fromEntity(saved);
    }
}
