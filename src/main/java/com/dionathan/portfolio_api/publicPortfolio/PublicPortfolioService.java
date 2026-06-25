package com.dionathan.portfolio_api.publicPortfolio;

import com.dionathan.portfolio_api.about.About;
import com.dionathan.portfolio_api.about.AboutMapper;
import com.dionathan.portfolio_api.about.AboutRepository;
import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.auth.UserRepository;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import com.dionathan.portfolio_api.project.Project;
import com.dionathan.portfolio_api.project.ProjectMapper;
import com.dionathan.portfolio_api.project.ProjectRepository;
import com.dionathan.portfolio_api.skills.Skill;
import com.dionathan.portfolio_api.skills.SkillMapper;
import com.dionathan.portfolio_api.skills.SkillRepository;
import com.dionathan.portfolio_api.timeline.Timeline;
import com.dionathan.portfolio_api.timeline.TimelineMapper;
import com.dionathan.portfolio_api.timeline.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicPortfolioService {

    private final AboutRepository aboutRepository;
    private final AboutMapper aboutMapper;
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final TimelineRepository timelineRepository;
    private final TimelineMapper timelineMapper;

    private final UserRepository userRepository;

    public @Nullable PortfolioResponseDTO getPortfolio(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User não encontrado"));

        About about = aboutRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("About nao encontrado"));

        List<Skill> skills = skillRepository.findByUser(user);
        List<Project> projects = projectRepository.findAllByUserAndFeaturedIsTrueAndDeletedIsFalse(user);
        List<Timeline> timelines = timelineRepository.findByUserOrderByStartDateDesc(user);

        return new PortfolioResponseDTO(
                aboutMapper.fromEntity(about),
                skills.stream().map(skillMapper::fromEntity).toList(),
                projects.stream().map(projectMapper::fromEntity).toList(),
                timelines.stream().map(timelineMapper::fromEntity).toList()

        );

    }
}
