package com.dionathan.portfolio_api.social;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.social.dto.SocialRequestDTO;
import com.dionathan.portfolio_api.social.dto.SocialResponseDTO;
import com.dionathan.portfolio_api.social.dto.SocialUpdateRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class SocialMapper {

    public Social toEntity(SocialRequestDTO requestDTO, User user) {
        Social social = new Social();

        social.setGithubUrl(requestDTO.githubUrl());
        social.setLinkedinUrl(requestDTO.linkedinUrl());
        social.setEmail(requestDTO.email());
        social.setWebsite(requestDTO.website());
        social.setUser(user);

        return social;
    }

    public SocialResponseDTO fromEntity(Social social) {
        return new SocialResponseDTO(
                social.getId(),
                social.getGithubUrl(),
                social.getLinkedinUrl(),
                social.getEmail(),
                social.getWebsite()
        );
    }

    public Social updateToEntity(SocialUpdateRequestDTO requestDTO, Social social) {

        if(requestDTO.githubUrl() != null) {
            social.setGithubUrl(requestDTO.githubUrl());
        }
        if(requestDTO.linkedinUrl() != null) {
            social.setLinkedinUrl(requestDTO.linkedinUrl());
        }
        if(requestDTO.email() != null) {
            social.setEmail(requestDTO.email());
        }
        if(requestDTO.website() != null) {
            social.setWebsite(requestDTO.website());
        }

        return social;
    }
}
