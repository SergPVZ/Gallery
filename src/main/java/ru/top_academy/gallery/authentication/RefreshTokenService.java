package ru.top_academy.gallery.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.top_academy.gallery.objeсkts.audience.Audience;
import ru.top_academy.gallery.objeсkts.audience.AudienceRepository;
import ru.top_academy.gallery.objeсkts.photographer.Photographer;
import ru.top_academy.gallery.objeсkts.photographer.PhotographerRepository;
import ru.top_academy.gallery.security.JwtService;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final JwtService jwtService;
    private final PhotographerRepository photographerRepository;
    private final AudienceRepository audienceRepository;

    @Transactional
    public AuthenticationResponse refreshToken(String refreshToken) {
        try {
            // Проверяем валидность refresh токена
            String email = jwtService.extractUsername(refreshToken);

            if (email == null) {
                throw new RuntimeException("Неверный refresh токен");
            }

            // Ищем пользователя
            var photographer = photographerRepository.findByEmail(email);
            var audience = audienceRepository.findByEmail(email);

            if (photographer.isPresent()) {
                Photographer user = photographer.get();
                return processRefreshToken(refreshToken, user);
            } else if (audience.isPresent()) {
                Audience user = audience.get();
                return processRefreshToken(refreshToken, user);
            } else {
                throw new RuntimeException("Пользователь не найден");
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка обновления токена: " + e.getMessage());
        }
    }

    private AuthenticationResponse processRefreshToken(String refreshToken, Object user) {
        // Проверяем, что пользователь активен
        if (user instanceof Photographer) {
            Photographer photographer = (Photographer) user;
            if (!photographer.isActive()) {
                throw new RuntimeException("Аккаунт фотографа отключен");
            }
        } else if (user instanceof Audience) {
            Audience audience = (Audience) user;
            if (!audience.isActive()) {
                throw new RuntimeException("Аккаунт зрителя отключен");
            }
        }

        // Проверяем валидность токена для пользователя
        boolean isValid = jwtService.isTokenValid(refreshToken, (UserDetails) user);
        if (!isValid) {
            throw new RuntimeException("Неверный или просроченный refresh токен");
        }

        // Генерируем новые токены
        String newAccessToken = jwtService.generateToken((UserDetails) user);
        String newRefreshToken = jwtService.generateRefreshToken((UserDetails) user);

        // Определяем роль пользователя
        String role;
        if (user instanceof Photographer) {
            role = "PHOTOGRAPHER";
        } else {
            role = "AUDIENCE";
        }

        // Создаем ответ
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(newAccessToken);
        response.setRefreshToken(newRefreshToken);
        response.setEmail(getUserEmail(user));
        response.setRole(role);

        return response;
    }

    private String getUserEmail(Object user) {
        if (user instanceof Photographer) {
            return ((Photographer) user).getEmail();
        } else if (user instanceof Audience) {
            return ((Audience) user).getEmail();
        }
        return null;
    }
}