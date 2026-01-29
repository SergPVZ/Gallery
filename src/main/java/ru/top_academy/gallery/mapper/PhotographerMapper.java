package ru.top_academy.gallery.mapper;

import org.springframework.stereotype.Component;
import ru.top_academy.gallery.entity.Photographer;
import ru.top_academy.gallery.dto.PhotographerResponseDTO;

@Component
public class PhotographerMapper {
    public PhotographerResponseDTO mapToPhotographerResponseDTO(Photographer photographer) {

        return new PhotographerResponseDTO(photographer.getId(), photographer.getFirstName(), photographer.getLastName(),
                photographer.getEmail(), photographer.getPhone(), photographer.getCity(),
                photographer.getRegistrationDate(), photographer.isActive(), photographer.getUpdatedAt());

    }

}
