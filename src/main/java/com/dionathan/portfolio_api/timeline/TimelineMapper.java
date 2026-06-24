package com.dionathan.portfolio_api.timeline;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.timeline.dto.TimelineRequestDTO;
import com.dionathan.portfolio_api.timeline.dto.TimelineResponseDTO;
import com.dionathan.portfolio_api.timeline.dto.TimelineUpdateRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimelineMapper {

    public Timeline toEntity(TimelineRequestDTO requestDTO, User user) {
        Timeline timeline = new Timeline();

        timeline.setType(requestDTO.type());
        timeline.setTitle(requestDTO.title());
        timeline.setSubtitle(requestDTO.subtitle());
        timeline.setDescription(requestDTO.description());
        timeline.setStartDate(requestDTO.startDate());
        timeline.setEndDate(requestDTO.endDate());
        timeline.setUser(user);

        return timeline;
    }

    public TimelineResponseDTO fromEntity(Timeline timeline) {
        return new TimelineResponseDTO(
                timeline.getId(),
                timeline.getType(),
                timeline.getTitle(),
                timeline.getSubtitle(),
                timeline.getDescription(),
                timeline.getStartDate(),
                timeline.getEndDate(),
                timeline.getCurrent(),
                timeline.getFeatured()
        );
    }

    public List<TimelineResponseDTO> fromEntity(List<Timeline> timelines) {
        return timelines.stream().map(this::fromEntity).toList();
    }

    public Timeline updateToEntity(TimelineUpdateRequestDTO requestDTO, Timeline timeline) {

        if(requestDTO.type() != null) {
            timeline.setType(requestDTO.type());
        }
        if(requestDTO.title() != null) {
            timeline.setTitle(requestDTO.title());
        }
        if(requestDTO.subtitle() != null) {
            timeline.setSubtitle(requestDTO.subtitle());
        }
        if(requestDTO.description() != null) {
            timeline.setDescription(requestDTO.description());
        }
        if(requestDTO.startDate() != null) {
            timeline.setStartDate(requestDTO.startDate());
        }
        if(requestDTO.endDate() != null) {
            timeline.setEndDate(requestDTO.endDate());
        }
        if(requestDTO.current() != null) {
            timeline.setCurrent(requestDTO.current());
        }
        if(requestDTO.featured() != null) {
            timeline.setFeatured(requestDTO.featured());
        }

        return timeline;
    }
}
