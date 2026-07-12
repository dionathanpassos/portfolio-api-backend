package com.dionathan.portfolio_api.social;

import com.dionathan.portfolio_api.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<Social, Long> {
    Optional<Social> findByUser(User user);

    Optional<Social> findByIdAndUser(Long id, User user);
}
