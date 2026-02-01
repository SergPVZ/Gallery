package ru.top_academy.gallery.photographer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, UUID> {

    Optional<Photographer> findByEmail(String email);   //добавить внутрь скобок @NotBlank @Email, как на занятиях

    @Query("SELECT p FROM photographer p WHERE p.firstName = :firstName AND p.lastName = :lastName")

    List<Photographer> findByFirstNameAndLastName(@Param("firstName") String firstName,
                                                  @Param("lastName") String lastName);

}
