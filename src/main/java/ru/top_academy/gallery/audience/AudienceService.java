package ru.top_academy.gallery.audience;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class AudienceService {

    @Autowired
    private AudienceRepository repository;

    @Autowired
    private AudienceMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public AudienceResponseDTO createNewAudience(@Valid AudienceRequest request) {

        Audience audience = new Audience();
        audience.setName(request.getName());
        audience.setEmail(request.getEmail());
        audience.setCity(request.getCity());
        audience.setActive(request.isActive());

        Audience newAudience = repository.save(audience);

        return mapper.mapToAudienceResponseDTO(newAudience);

    }

    @Transactional(readOnly = true)
    public AudienceResponseDTO getAudience(UUID id) {

        Audience audience = repository.findById(id).orElse(null);

        return mapper.mapToAudienceResponseDTO(audience);

    }

    @Transactional//(rollbackFor = Exception.class) - выяснить и может быть вернуть, как на занятиях
    public AudienceResponseDTO updateAudience(UUID id, @Valid AudienceRequest request) {

        Audience audience = repository.findById(id).orElse(null);
        audience.setName(request.getName());
        audience.setEmail(request.getEmail());
        audience.setCity(request.getCity());
        audience.setActive(request.isActive());
        audience.setUpdatedAt(LocalDateTime.now());

        Audience updatedAudience = repository.saveAndFlush(audience);

        return mapper.mapToAudienceResponseDTO(updatedAudience);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(UUID id) {

        Audience audience = repository.findById(id).orElse(null);

        repository.delete(audience);

    }

    public List<AudienceResponseDTO> getAudienceByName(String name) {

        List<Audience> audiences = repository.findByFirstName(name);

        return audiences.stream()
                .map(mapper::mapToAudienceResponseDTO)
                .collect(Collectors.toList());

    }

}
