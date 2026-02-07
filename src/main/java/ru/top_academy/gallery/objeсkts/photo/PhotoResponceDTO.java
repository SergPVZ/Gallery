package ru.top_academy.gallery.objeсkts.photo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "DTO с основной информацией для работы с фотографиями")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponceDTO {

    @Schema(description ="Идентификатор фотогрвфии")
    private UUID id;

    @Schema(description ="Название фотогрвфии")
    private String name;

    @Schema(description ="Жанр")
    private String genre;

    @Schema(description ="Дата загрузки на сайт")
    private LocalDateTime downloadDateTime;

}
