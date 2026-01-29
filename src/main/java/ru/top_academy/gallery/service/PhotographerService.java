package ru.top_academy.gallery.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.top_academy.gallery.dto.PhotographerResponseDTO;
import ru.top_academy.gallery.entity.Photographer;
import ru.top_academy.gallery.mapper.PhotographerMapper;
import ru.top_academy.gallery.repository.PhotographerRepository;
import ru.top_academy.gallery.request.PhotographerRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class PhotographerService {

    @Autowired
    private PhotographerRepository repository;

    @Autowired
    private PhotographerMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public PhotographerResponseDTO createNewPhotographer(@Valid PhotographerRequest request) {

        Photographer photographer = new Photographer();
        photographer.setFirstName(request.getFirstName());
        photographer.setLastName(request.getLastName());
        photographer.setEmail(request.getEmail());
        photographer.setPhone(request.getPhone());
        photographer.setCity(request.getCity());
        photographer.setActive(request.isActive());

        Photographer newPhotographer = repository.save(photographer);

        return mapper.mapToPhotographerResponseDTO(newPhotographer);

    }

    @Transactional(readOnly = true)
    public PhotographerResponseDTO getPhotographer(UUID id) {

        Photographer photographer = repository.findById(id).orElse(null);

        return mapper.mapToPhotographerResponseDTO(photographer);

    }

    @Transactional//(rollbackFor = Exception.class) - выяснить и может быть вернуть, как на занятиях
    public PhotographerResponseDTO updatePhotographer(UUID id, @Valid PhotographerRequest request) {

        Photographer photographer = repository.findById(id).orElse(null);
        photographer.setFirstName(request.getFirstName());
        photographer.setLastName(request.getLastName());
        photographer.setEmail(request.getEmail());
        photographer.setPhone(request.getPhone());
        photographer.setCity(request.getCity());
        photographer.setActive(request.isActive());
        photographer.setUpdatedAt(LocalDateTime.now());

        Photographer updatedPhotographer = repository.saveAndFlush(photographer);

        return mapper.mapToPhotographerResponseDTO(updatedPhotographer);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(UUID id) {

        Photographer photographer = repository.findById(id).orElse(null);

        repository.delete(photographer);

    }

    public List<PhotographerResponseDTO> getPhotographerByName(String firstName, String lastName) {

        List<Photographer> getFirstAndLastName = repository.findByFirstNameAndLastName(firstName, lastName);

        return getFirstAndLastName.stream()
                .map(mapper::mapToPhotographerResponseDTO)
                .collect(Collectors.toList());

    }
}
