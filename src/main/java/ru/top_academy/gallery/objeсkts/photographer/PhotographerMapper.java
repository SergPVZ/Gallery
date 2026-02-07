package ru.top_academy.gallery.objeсkts.photographer;

import org.springframework.stereotype.Component;

@Component
public class PhotographerMapper {
    public PhotographerResponseDTO mapToPhotographerResponseDTO(Photographer photographer) {

        return new PhotographerResponseDTO(photographer.getId(), photographer.getFirstName(), photographer.getLastName(),
                photographer.getEmail(), photographer.getPhone(), photographer.getCity(),
                photographer.getRegistrationDate(), photographer.isActive(), photographer.getUpdatedAt());

    }

}
