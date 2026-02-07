package ru.top_academy.gallery.objeсkts.audience;

import org.springframework.stereotype.Component;

@Component
public class AudienceMapper {
    public AudienceResponseDTO mapToAudienceResponseDTO(Audience audience) {

        return new AudienceResponseDTO(audience.getId(), audience.getName(),
                audience.getEmail(), audience.getCity(),
                audience.getRegistrationDate(), audience.isActive(), audience.getUpdatedAt());

    }

}
