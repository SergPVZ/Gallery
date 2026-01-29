package ru.top_academy.gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GalleryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryApplication.class, args);
    }

}
