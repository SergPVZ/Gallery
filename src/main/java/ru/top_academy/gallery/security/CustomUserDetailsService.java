package ru.top_academy.gallery.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.top_academy.gallery.objeсkts.audience.AudienceRepository;
import ru.top_academy.gallery.objeсkts.photographer.PhotographerRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PhotographerRepository photographerRepository;
    private final AudienceRepository audienceRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Сначала ищем среди фотографов
        var photographer = photographerRepository.findByEmail(email);
        if (photographer.isPresent()) {
            var p = photographer.get();
            return User.builder()
                    .username(p.getEmail())
                    .password(p.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + p.getRole())))
                    .disabled(!p.isActive())
                    .build();
        }

        // Затем среди аудитории
        var audience = audienceRepository.findByEmail(email);
        if (audience.isPresent()) {
            var a = audience.get();
            return User.builder()
                    .username(a.getEmail())
                    .password(a.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + a.getRole())))
                    .disabled(!a.isActive())
                    .build();
        }

        throw new UsernameNotFoundException("Пользователь не найден с email: " + email);
    }
}