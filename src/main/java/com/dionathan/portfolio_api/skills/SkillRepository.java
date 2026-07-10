package com.dionathan.portfolio_api.skills;

import com.dionathan.portfolio_api.auth.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    boolean existsByNameAndUser(String name, User user);

    Optional<Skill> findByIdAndUser(Long id, User user);

    Page<Skill> findAllByUserAndDeletedIsFalse(Pageable pageable, User user);

    List<Skill> findByUser(User user);

    List<Skill> findByUserAndDeletedIsFalse(User user);
}
