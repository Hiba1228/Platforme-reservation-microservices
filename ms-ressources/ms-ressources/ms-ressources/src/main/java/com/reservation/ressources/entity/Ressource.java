package com.reservation.ressources.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ressources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String nom;

    @NotNull(message = "Le type est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeRessource type;

    @NotNull(message = "La capacité est obligatoire")
    @Min(value = 1)
    @Column(nullable = false)
    private Integer capacite;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false)
    private Double prixParJour;

    @Column(nullable = false)
    private Boolean disponible = true;

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String adresse;

    @Column(length = 1000)
    private String equipements;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}