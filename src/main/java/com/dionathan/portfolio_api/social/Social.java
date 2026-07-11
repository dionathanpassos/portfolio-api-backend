package com.dionathan.portfolio_api.social;

import com.dionathan.portfolio_api.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_social")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "github_url", nullable = false)
    private String githubUrl;

    @Column(name = "linkedin_url", nullable = false)
    private String linkedinUrl;

    @Column(nullable = false)
    private String email;

    private String website;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
