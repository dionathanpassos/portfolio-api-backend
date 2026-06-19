package com.dionathan.portfolio_api.about;

import com.dionathan.portfolio_api.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_about")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String bio;

    private String location;

    @Column(name = "github_url", nullable = false)
    private String githubUrl;

    @Column(name = "linkedin_url", nullable = false)
    private String linkedinUrl;

    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

}
