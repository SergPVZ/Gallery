package ru.top_academy.gallery.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO запроса загрузки фотографии")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRequest {

    @Schema(description = "Название фотографии")
    @JsonProperty("name")
    @NotBlank
    private String name;

    @Schema(description = "Жанр")
    @JsonProperty("genre")
    @NotBlank
    private String genre;

}
