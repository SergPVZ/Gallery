package ru.top_academy.gallery.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {

    @Query("SELECT p FROM photo p WHERE p.name = :name")
    List<Photo> findByName(@Param("name") String name);


    @Query("SELECT p FROM photo p WHERE p.genre = :genre")
    List<Photo> findByGenre(@Param("genre") String genre);

}

