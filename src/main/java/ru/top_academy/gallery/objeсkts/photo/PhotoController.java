package ru.top_academy.gallery.objeсkts.photo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/photo")
//@PreAuthorize("hasAnyRole('PHOTOGRAPHER', 'AUDIENCE')")
public class PhotoController {

    @Autowired
    private PhotoService service;

    @PostMapping("/addNewPhoto")
    public ResponseEntity<PhotoResponceDTO> addNewPhoto(@Valid  @RequestBody PhotoRequest request) {

        PhotoResponceDTO photoFile = service.download(request);

        return ResponseEntity.ok(photoFile);

    }

    @GetMapping("/{id} getById")
    public ResponseEntity<PhotoResponceDTO> getPhotoById(@PathVariable UUID id) {

        PhotoResponceDTO getPhoto = service.getDataPhoto(id);

        return ResponseEntity.ok(getPhoto);

    }

    @GetMapping("/{id} getByName")
    public ResponseEntity<List<PhotoResponceDTO>> getPhotoByName(@RequestParam String name) {

        List<PhotoResponceDTO> photo = service.getPhotosByName(name);

        return ResponseEntity.ok(photo);

    }

    @GetMapping("/{id} getByGenre")
    public ResponseEntity<List<PhotoResponceDTO>> getPhotoByGenre(@RequestParam String genre) {

        List<PhotoResponceDTO> photo = service.getPhotosByGenre(genre);

        return ResponseEntity.ok(photo);

    }

}
