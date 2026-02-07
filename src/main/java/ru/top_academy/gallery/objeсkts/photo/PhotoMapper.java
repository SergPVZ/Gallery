package ru.top_academy.gallery.objeсkts.photo;

import org.springframework.stereotype.Component;

@Component
public class PhotoMapper {

    public PhotoResponceDTO mapToPhotoResponceDTO(Photo photo) {
        return new PhotoResponceDTO (photo.getId(), photo.getName(), photo.getGenre(), photo.getDownloadDateTime());
    }

}
