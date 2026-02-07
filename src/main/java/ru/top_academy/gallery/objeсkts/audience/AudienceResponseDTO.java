package ru.top_academy.gallery.objeсkts.audience;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "DTO с основной информацией для работы с посетителем")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudienceResponseDTO {

    @Schema(description ="Идентификатор посетителя")
    private UUID id;

    @Schema(description ="Имя посетителя")
    private String name;

    @Schema(description ="Электронная почта посетителя")
    private String email;

    @Schema(description ="Город регистрации посетителя")
    private String city;

    @Schema(description ="Дата регистрации на сайте")
    private LocalDateTime registrationDate;

    @Schema(description = "Наличие активности посетителя")
    private boolean isActive;

    @Schema(description = " /// ")
    private LocalDateTime updatedAt;

}
