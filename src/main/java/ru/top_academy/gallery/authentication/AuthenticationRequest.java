package ru.top_academy.gallery.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для запроса аутентификации")
public class AuthenticationRequest {

    @Schema(description = "Email пользователя")
    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private String email;

    @Schema(description = "Пароль")
    @NotBlank(message = "Пароль обязателен")
    private String password;

    @Schema(description = "Тип пользователя: PHOTOGRAPHER или AUDIENCE")
    @NotBlank(message = "Тип пользователя обязателен")
    private String userType;
}