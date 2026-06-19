package com.dionathan.portfolio_api.about;

import com.dionathan.portfolio_api.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Long> {
    Optional<About> findByUser(User user);
}
