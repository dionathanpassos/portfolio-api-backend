package com.dionathan.portfolio_api.about;

import com.dionathan.portfolio_api.about.dto.AboutRequestDTO;
import com.dionathan.portfolio_api.about.dto.AboutResponseDTO;
import com.dionathan.portfolio_api.about.dto.AboutUpdateRequestDTO;
import com.dionathan.portfolio_api.auth.User;
import org.springframework.stereotype.Component;

@Component
public class AboutMapper {

    public About toEntity(AboutRequestDTO requestDTO, User user) {
        About about = new About();

        about.setName(requestDTO.name());
        about.setTitle(requestDTO.title());
        about.setBio(requestDTO.bio());
        about.setLocation(requestDTO.location());
        about.setGithubUrl(requestDTO.githubUrl());
        about.setLinkedinUrl(requestDTO.linkedinUrl());
        about.setEmail(requestDTO.email());
        about.setUser(user);

        return about;
    }

    public AboutResponseDTO fromEntity(About about) {
        return new AboutResponseDTO(
                about.getId(),
                about.getName(),
                about.getTitle(),
                about.getBio(),
                about.getLocation(),
                about.getGithubUrl(),
                about.getLinkedinUrl(),
                about.getEmail()

        );
    }

    public About updateToEntiy(AboutUpdateRequestDTO requestDTO, About about) {

        if(requestDTO.name() != null) {
            about.setName(requestDTO.name());
        }
        if(requestDTO.title() != null) {
            about.setTitle(requestDTO.title());
        }
        if(requestDTO.bio() != null) {
            about.setBio(requestDTO.bio());
        }
        if(requestDTO.location() != null) {
            about.setLocation(requestDTO.location());
        }
        if(requestDTO.githubUrl() != null) {
            about.setGithubUrl(requestDTO.githubUrl());
        }
        if(requestDTO.linkedinUrl() != null) {
            about.setLinkedinUrl(requestDTO.linkedinUrl());
        }
        if(requestDTO.email() != null) {
            about.setEmail(requestDTO.email());
        }

        return about;
    }
}
