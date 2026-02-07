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
@Schema(description = "DTO для запроса регистрации")
public class RegisterRequest {

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

    // Общие поля
    @Schema(description = "Имя (для фотографа) или ник (для аудитории)")
    @NotBlank(message = "Имя/ник обязателен")
    private String name;

    @Schema(description = "Город")
    @NotBlank(message = "Город обязателен")
    private String city;

    // Поля только для фотографа
    @Schema(description = "Фамилия фотографа (только для PHOTOGRAPHER)")
    private String lastName;

    @Schema(description = "Телефон фотографа (только для PHOTOGRAPHER)")
    private String phone;
}