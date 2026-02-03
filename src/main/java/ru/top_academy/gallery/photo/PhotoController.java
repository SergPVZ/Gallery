package ru.top_academy.gallery.photo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/photo")
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

}
