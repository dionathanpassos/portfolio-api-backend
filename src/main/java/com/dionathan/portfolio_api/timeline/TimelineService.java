package com.dionathan.portfolio_api.timeline;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.exception.BusinessException;
import com.dionathan.portfolio_api.exception.ResourceNotFoundException;
import com.dionathan.portfolio_api.security.AuthenticatedUserService;
import com.dionathan.portfolio_api.timeline.dto.TimelineRequestDTO;
import com.dionathan.portfolio_api.timeline.dto.TimelineResponseDTO;
import com.dionathan.portfolio_api.timeline.dto.TimelineUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TimelineService {

    private final TimelineRepository timelineRepository;
    private final TimelineMapper timelineMapper;
    private final AuthenticatedUserService authenticatedUserService;

    @Transactional
    public TimelineResponseDTO create(TimelineRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        if(requestDTO.endDate() != null && requestDTO.endDate().isBefore(requestDTO.startDate())) {
            throw new BusinessException("A data final não pode ser anterior a data inicial");
        }
        if(requestDTO.current() && requestDTO.endDate() != null) {
            throw new BusinessException("A experiência atual não pode ter uma data de término.");
        }
        if(!requestDTO.current() && requestDTO.endDate() == null) {
            throw new BusinessException("A data de término é obrigatória se não for o trabalho atual");
        }

        Timeline timeline = timelineMapper.toEntity(requestDTO, user);
        Timeline saved = timelineRepository.save(timeline);

        return timelineMapper.fromEntity(saved);

    }

    @Transactional(readOnly = true)
    public TimelineResponseDTO findById(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Timeline timeline = timelineRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Experiência não encontrada"));

        return timelineMapper.fromEntity(timeline);
    }

    @Transactional(readOnly = true)
    public Page<TimelineResponseDTO> findAll(Pageable pageable) {
        User user = authenticatedUserService.getAuthenticatedUser();

        return timelineRepository.findAllByUserOrderByStartDateDesc(user, pageable).map(timelineMapper::fromEntity);
    }

    @Transactional
    public TimelineResponseDTO update(Long id, TimelineUpdateRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Timeline timeline = timelineRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Experiência não encontrada"));

        LocalDate startDate = requestDTO.startDate() != null
                ? requestDTO.startDate()
                : timeline.getStartDate();

        LocalDate endDate = requestDTO.endDate() != null
                ? requestDTO.endDate()
                : timeline.getEndDate();

        Boolean current = requestDTO.current() != null
                ? requestDTO.current()
                : timeline.getCurrent();

        if(endDate != null && endDate.isBefore(startDate)) {
            throw new BusinessException("A data final não pode ser anterior à data inicial");
        }

        if(current && endDate != null) {
            throw new BusinessException("A experiência atual não pode ter uma data de término.");
        }

        if(!current && endDate == null) {
            throw new BusinessException("A data de término é obrigatória se não for o trabalho atual");
        }

        timeline = timelineMapper.updateToEntity(requestDTO, timeline);
        Timeline saved = timelineRepository.save(timeline);

        return timelineMapper.fromEntity(saved);

    }
}
