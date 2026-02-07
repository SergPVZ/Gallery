package ru.top_academy.gallery.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для ответа аутентификации")
public class AuthenticationResponse {

    @Schema(description = "JWT токен")
    private String accessToken;

    @Schema(description = "Refresh токен")
    private String refreshToken;

    @Schema(description = "Тип токена")
    private final String tokenType = "Bearer";

    @Schema(description = "Email пользователя")
    private String email;

    @Schema(description = "Роль пользователя")
    private String role;

}
