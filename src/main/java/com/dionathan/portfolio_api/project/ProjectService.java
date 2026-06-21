package com.dionathan.portfolio_api.project;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.exception.BusinessException;
import com.dionathan.portfolio_api.exception.ConflictException;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import com.dionathan.portfolio_api.project.dto.ProjectRequestDTO;
import com.dionathan.portfolio_api.project.dto.ProjectResponseDTO;
import com.dionathan.portfolio_api.project.dto.ProjectUpdateRequestDTO;
import com.dionathan.portfolio_api.security.AuthenticatedUserService;
import com.dionathan.portfolio_api.skills.Skill;
import com.dionathan.portfolio_api.skills.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final AuthenticatedUserService authenticatedUserService;
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;
    private final ProjectMapper projectMapper;

    @Transactional
    public ProjectResponseDTO create(ProjectRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        if(projectRepository.existsBySlugAndUser(requestDTO.slug().trim(), user)) {
            throw new ConflictException("Projeto já cadastrado");
        }

        Set<Skill> skills = new HashSet<>(skillRepository.findAllById(requestDTO.skillsIds()));

        if(skills.size() != requestDTO.skillsIds().size()) {
            throw new ResourceNotFoundException("Uma ou mais skills não foram encontradas");
        }

        Project project = projectMapper.toEntity(requestDTO, skills, user);
        Project saved = projectRepository.save(project);

        return projectMapper.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public ProjectResponseDTO findById(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Project project = projectRepository.findByIdAndUserAndDeletedIsFalse(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        return projectMapper.fromEntity(project);
    }

    @Transactional(readOnly = true)
    public Page<ProjectResponseDTO> findAll(Pageable pageable) {
        User user = authenticatedUserService.getAuthenticatedUser();

        return projectRepository.findAllByUserAndFeaturedIsTrueAndDeletedIsFalse(user, pageable).map(projectMapper::fromEntity);
    }

    @Transactional
    public ProjectResponseDTO update(Long id, ProjectUpdateRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Project project = projectRepository.findByIdAndUserAndDeletedIsFalse(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontado"));

        Set<Skill> skills = new HashSet<>();

        if(requestDTO.skillsIds() != null) {
            skills = new HashSet<>(skillRepository.findAllById(requestDTO.skillsIds()));

            if(skills.size() != requestDTO.skillsIds().size()) {
                throw new ResourceNotFoundException("Uma ou mais skills não foram encontradas");
            }
        }

        project = projectMapper.updateToEntity(project, requestDTO, skills);
        Project saved = projectRepository.save(project);

        return projectMapper.fromEntity(saved);

    }

    @Transactional
    public void delete(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Project project = projectRepository.findByIdAndUserAndDeletedIsFalse(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontado"));

        if(project.isDeleted()) {
            throw new BusinessException("Projeto já excluído");
        }
        project.setDeleted(true);

    }
}
