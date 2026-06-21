package com.dionathan.portfolio_api.project;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.project.dto.ProjectRequestDTO;
import com.dionathan.portfolio_api.project.dto.ProjectResponseDTO;
import com.dionathan.portfolio_api.project.dto.ProjectUpdateRequestDTO;
import com.dionathan.portfolio_api.skills.Skill;
import com.dionathan.portfolio_api.skills.dto.SkillResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public Project toEntity(ProjectRequestDTO requestDTO, Set<Skill> skills, User user) {
        Project project = new Project();

        project.setTitle(requestDTO.title());
        project.setDescription(requestDTO.description());
        project.setSlug(requestDTO.slug());
        project.setRepositoryUrl(requestDTO.repositoryUrl());
        project.setDemoUrl(requestDTO.demoUrl());
        project.setSkills(skills);
        project.setUser(user);

        return project;
    }


    public ProjectResponseDTO fromEntity(Project project) {
        Set<Skill> skills = project.getSkills();

        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getSlug(),
                project.getRepositoryUrl(),
                project.getDemoUrl(),
                project.getFeatured(),
                project.getStatus(),
                project.getCreatedAt(),
                project.getSkills()
                        .stream()
                        .map(skill -> new SkillResponseDTO(
                                skill.getId(),
                                skill.getName(),
                                skill.getCategory(),
                                skill.getIconUrl(),
                                skill.getLevel(),
                                skill.isActive()

                        ))
                        .collect(Collectors.toSet())

        );
    }

    public Project updateToEntity(Project project, ProjectUpdateRequestDTO requestDTO, Set<Skill> skills) {

        if(requestDTO.title() != null) {
            project.setTitle(requestDTO.title());
        }
        if(requestDTO.description() != null) {
            project.setDescription(requestDTO.description());
        }
        if(requestDTO.slug() != null) {
            project.setSlug(requestDTO.slug());
        }
        if(requestDTO.repositoryUrl() != null) {
            project.setRepositoryUrl(requestDTO.repositoryUrl());
        }
        if(requestDTO.demoUrl() != null) {
            project.setDemoUrl(requestDTO.demoUrl());
        }
        if(requestDTO.featured() != null) {
            project.setFeatured(requestDTO.featured());
        }
        if(requestDTO.status() != null) {
            project.setStatus(requestDTO.status());
        }
        if(requestDTO.skillsIds() != null) {
            project.setSkills(skills);
        }

        return project;

    }
}
