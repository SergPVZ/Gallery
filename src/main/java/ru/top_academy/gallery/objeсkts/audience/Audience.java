package ru.top_academy.gallery.objeсkts.audience;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;
import java.time.LocalDateTime;

@Entity(name = "audience")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Audience {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String city;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "AUDIENCE";

    @CreationTimestamp
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Column
    private boolean active;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
