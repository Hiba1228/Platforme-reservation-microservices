package com.reservation.ms_reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RessourceDTO {
    private Long id;
    private String nom;
    private String type;
    private Integer capacite;
    private Double prixParJour;
    private Boolean disponible;
    private String description;
    private String adresse;
}