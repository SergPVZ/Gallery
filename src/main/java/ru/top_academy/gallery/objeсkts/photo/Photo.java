package ru.top_academy.gallery.objeсkts.photo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Photo {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @CreationTimestamp
    @Column(name = "download", nullable = false, updatable = false)
    private LocalDateTime downloadDateTime;

}
