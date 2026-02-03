package ru.top_academy.gallery.audience;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO запроса создания пользователя")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudienceRequest {

    @Schema(description = "Имя пользователя")
    @JsonProperty("name")
    @NotBlank
    private String name;

    @Schema(description = "Электронная почта пользователя")
    @JsonProperty("email")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Часовой пояс пользователя")
    @JsonProperty("city")
    @NotBlank
    private String city;

    // удалить, скорее всего не нужно
    @Schema(description = "Наличие активности пользователя",
            defaultValue = "true")
    @JsonProperty("isActive")
    private boolean isActive = true;

}
