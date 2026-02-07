package ru.top_academy.gallery.objeсkts.audience;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/audience")
@PreAuthorize("hasRole('AUDIENCE')")
public class AudienceController {

    @Autowired
    private AudienceService service;

    @PostMapping("/adNewAudiences")
    public ResponseEntity<AudienceResponseDTO> createAudiences(@Valid @RequestBody AudienceRequest request) {

        AudienceResponseDTO audience = service.createNewAudience(request);

        return ResponseEntity.ok(audience);

    }

    @GetMapping("/{id} getById")
    public ResponseEntity<AudienceResponseDTO> getAudiencesById(@PathVariable UUID id) {

        AudienceResponseDTO audience = service.getAudience(id);

        return ResponseEntity.ok(audience);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AudienceResponseDTO> updateAudienceById(@PathVariable UUID id,
                                                                  @Valid @RequestBody AudienceRequest request) {

        AudienceResponseDTO audience = service.updateAudience(id, request);

        return ResponseEntity.ok(audience);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AudienceResponseDTO> deleteAudienceById(@PathVariable UUID id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id} getByName")
    public ResponseEntity<List<AudienceResponseDTO >> getAudienceByName(@RequestParam String name) {

        List<AudienceResponseDTO> audience = service.getAudienceByName(name);

        return ResponseEntity.ok(audience);

    }

}
