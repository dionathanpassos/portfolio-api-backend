package com.dionathan.portfolio_api.about;

import com.dionathan.portfolio_api.about.dto.AboutRequestDTO;
import com.dionathan.portfolio_api.about.dto.AboutResponseDTO;
import com.dionathan.portfolio_api.about.dto.AboutUpdateRequestDTO;
import com.dionathan.portfolio_api.auth.User;
import org.springframework.stereotype.Component;

@Component
public class AboutMapper {

    public About toEntity(AboutRequestDTO requestDTO, User user) {
        About about = new About();

        about.setTitle(requestDTO.title());
        about.setParagraphOne(requestDTO.paragraphOne());
        about.setParagraphTwo(requestDTO.paragraphTwo());
        about.setParagraphThree(requestDTO.paragraphThree());
        about.setUser(user);

        return about;
    }

    public AboutResponseDTO fromEntity(About about) {
        return new AboutResponseDTO(
                about.getId(),
                about.getTitle(),
                about.getParagraphOne(),
                about.getParagraphTwo(),
                about.getParagraphThree()
        );

    }

    public About updateToEntiy(AboutUpdateRequestDTO requestDTO, About about) {

        if(requestDTO.title() != null) {
            about.setTitle(requestDTO.title());
        }
        if(requestDTO.paragraphOne() != null) {
            about.setParagraphOne(requestDTO.paragraphOne());
        }
        if(requestDTO.paragraphTwo() != null) {
            about.setParagraphTwo(requestDTO.paragraphTwo());
        }
        if(requestDTO.paragraphThree() != null) {
            about.setParagraphThree(requestDTO.paragraphThree());
        }

        return about;
    }
}
