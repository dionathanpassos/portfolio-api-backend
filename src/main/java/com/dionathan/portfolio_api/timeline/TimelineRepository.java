package com.dionathan.portfolio_api.timeline;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.timeline.dto.TimelineResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    Optional<Timeline> findByIdAndUser(Long id, User user);

    Page<Timeline> findAllByUserOrderByStartDateDesc(User user, Pageable pageable);
    List<Timeline> findByUserOrderByStartDateDesc(User user);
}
