package ru.top_academy.gallery.objeсkts.photographer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;
import java.time.LocalDateTime;

@Entity(name = "photographer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Photographer {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column
    private UUID id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String city;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "PHOTOGRAPHER";

    @CreationTimestamp
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Column
    private boolean active;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
