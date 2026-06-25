package com.dionathan.portfolio_api.project;

import com.dionathan.portfolio_api.auth.User;
import com.dionathan.portfolio_api.project.dto.ProjectResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByIdAndUserAndDeletedIsFalse(Long id, User user);

    Page<Project> findAllByUserAndFeaturedIsTrueAndDeletedIsFalse(User user, Pageable pageable);
    List<Project> findAllByUserAndFeaturedIsTrueAndDeletedIsFalse(User user);

    boolean existsBySlugAndUser(String slug, User user);
}
