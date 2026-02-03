package ru.top_academy.gallery.audience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AudienceRepository extends JpaRepository<Audience, UUID> {

    Optional<Audience> findByEmail(String email);   //добавить внутрь скобок @NotBlank @Email, как на занятиях

    @Query("SELECT p FROM audience p WHERE p.name = :name")
    List<Audience> findByFirstName(@Param("name") String name);

}
