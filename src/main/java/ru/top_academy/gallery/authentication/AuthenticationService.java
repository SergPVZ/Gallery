package ru.top_academy.gallery.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.top_academy.gallery.objeсkts.audience.Audience;
import ru.top_academy.gallery.objeсkts.audience.AudienceRepository;
import ru.top_academy.gallery.objeсkts.photographer.Photographer;
import ru.top_academy.gallery.objeсkts.photographer.PhotographerRepository;
import ru.top_academy.gallery.security.JwtService;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final PhotographerRepository photographerRepository;
    private final AudienceRepository audienceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        // Валидация типа пользователя
        if (!"PHOTOGRAPHER".equalsIgnoreCase(request.getUserType()) &&
                !"AUDIENCE".equalsIgnoreCase(request.getUserType())) {
            throw new IllegalArgumentException("Неверный тип пользователя. Используйте PHOTOGRAPHER или AUDIENCE");
        }

        // Проверка на существующий email
        if (photographerRepository.findByEmail(request.getEmail()).isPresent() ||
                audienceRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email уже зарегистрирован");
        }

        if ("PHOTOGRAPHER".equalsIgnoreCase(request.getUserType())) {
            return registerPhotographer(request);
        } else {
            return registerAudience(request);
        }
    }

    private AuthenticationResponse registerPhotographer(RegisterRequest request) {
        // Дополнительная валидация для фотографа
        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия обязательна для фотографа");
        }

        // Создание фотографа без builder
        Photographer photographer = new Photographer();
        photographer.setFirstName(request.getName());
        photographer.setLastName(request.getLastName());
        photographer.setEmail(request.getEmail());
        photographer.setPassword(passwordEncoder.encode(request.getPassword()));
        photographer.setPhone(request.getPhone());
        photographer.setCity(request.getCity());
        photographer.setRole("PHOTOGRAPHER");
        photographer.setActive(true);

        photographerRepository.save(photographer);

        String jwtToken = jwtService.generateToken((UserDetails) photographer);
        String refreshToken = jwtService.generateRefreshToken((UserDetails) photographer);

        // Создание ответа без builder
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(jwtToken);
        response.setRefreshToken(refreshToken);
        response.setEmail(photographer.getEmail());
        response.setRole(photographer.getRole());

        return response;
    }

    private AuthenticationResponse registerAudience(RegisterRequest request) {
        // Создание аудитории без builder
        Audience audience = new Audience();
        audience.setName(request.getName());
        audience.setEmail(request.getEmail());
        audience.setPassword(passwordEncoder.encode(request.getPassword()));
        audience.setCity(request.getCity());
        audience.setRole("AUDIENCE");
        audience.setActive(true);

        audienceRepository.save(audience);

        String jwtToken = jwtService.generateToken((UserDetails) audience);
        String refreshToken = jwtService.generateRefreshToken((UserDetails) audience);

        // Создание ответа без builder
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(jwtToken);
        response.setRefreshToken(refreshToken);
        response.setEmail(audience.getEmail());
        response.setRole(audience.getRole());

        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неверный email или пароль");
        }

        var photographer = photographerRepository.findByEmail(request.getEmail());
        var audience = audienceRepository.findByEmail(request.getEmail());

        if (photographer.isPresent()) {
            var user = photographer.get();
            if (!user.isActive()) {
                throw new RuntimeException("Аккаунт отключен");
            }

            // Проверка соответствия типа пользователя
            if (!"PHOTOGRAPHER".equalsIgnoreCase(request.getUserType())) {
                throw new IllegalArgumentException("Неверный тип пользователя для данного email");
            }

            String jwtToken = jwtService.generateToken((UserDetails) user);
            String refreshToken = jwtService.generateRefreshToken((UserDetails) user);

            // Создание ответа без builder
            AuthenticationResponse response = new AuthenticationResponse();
            response.setAccessToken(jwtToken);
            response.setRefreshToken(refreshToken);
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());

            return response;

        } else if (audience.isPresent()) {
            var user = audience.get();
            if (!user.isActive()) {
                throw new RuntimeException("Аккаунт отключен");
            }

            // Проверка соответствия типа пользователя
            if (!"AUDIENCE".equalsIgnoreCase(request.getUserType())) {
                throw new IllegalArgumentException("Неверный тип пользователя для данного email");
            }

            String jwtToken = jwtService.generateToken((UserDetails) user);
            String refreshToken = jwtService.generateRefreshToken((UserDetails) user);

            // Создание ответа без builder
            AuthenticationResponse response = new AuthenticationResponse();
            response.setAccessToken(jwtToken);
            response.setRefreshToken(refreshToken);
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());

            return response;
        }

        throw new RuntimeException("Пользователь не найден");

    }

}
