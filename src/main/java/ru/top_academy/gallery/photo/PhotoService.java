package ru.top_academy.gallery.photo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Transactional
@Validated
public class PhotoService {

    @Autowired
    private PhotoRepository repository;

    @Autowired
    private PhotoMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public PhotoResponceDTO download(@Valid PhotoRequest request) {

        Photo photo = new Photo();
        photo.setName(request.getName());
        photo.setGenre(request.getGenre());

        Photo newPhoto = repository.save(photo);

        return mapper.mapToPhotoResponceDTO(newPhoto);

    }

    @Transactional(readOnly = true)
    public PhotoResponceDTO getDataPhoto(UUID id) {

        Photo photo = repository.findById(id).orElse(null);

        return mapper.mapToPhotoResponceDTO(photo);

    }

}
