package ru.top_academy.gallery.photographer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/photographer")
public class PhotographerController {

    @Autowired
    private PhotographerService service;

    @PostMapping("/adNewPhotographer")
    public ResponseEntity<PhotographerResponseDTO> createPhotographer(@Valid @RequestBody PhotographerRequest request) {

        PhotographerResponseDTO photographer = service.createNewPhotographer(request);

        return ResponseEntity.ok(photographer);

    }

    @GetMapping("/{id} getById")
    public ResponseEntity<PhotographerResponseDTO> getPhotographerById(@PathVariable UUID id) {

        PhotographerResponseDTO photographer = service.getPhotographer(id);

        return ResponseEntity.ok(photographer);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotographerResponseDTO> updatePhotographerById(@PathVariable UUID id,
                                                                      @Valid @RequestBody PhotographerRequest request) {

        PhotographerResponseDTO photographer = service.updatePhotographer(id, request);

        return ResponseEntity.ok(photographer);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhotographerResponseDTO> deletePhotographerById(@PathVariable UUID id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id} getByName")
    public ResponseEntity<List<PhotographerResponseDTO >> getPhotographerByName(@RequestParam String firstName,
                                                                                @RequestParam String lastName) {

        List<PhotographerResponseDTO> photographer = service.getPhotographerByName(firstName, lastName);

        return ResponseEntity.ok(photographer);

    }

}
