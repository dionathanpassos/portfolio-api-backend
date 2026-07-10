package com.dionathan.portfolio_api.hero;

import com.dionathan.portfolio_api.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    Optional<Hero> findByUser(User user);
}
