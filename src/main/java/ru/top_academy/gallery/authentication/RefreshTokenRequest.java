package ru.top_academy.gallery.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для запроса обновления токена")
public class RefreshTokenRequest {

    @Schema(description = "Refresh токен")
    @NotBlank(message = "Refresh токен обязателен")
    private String refreshToken;
}