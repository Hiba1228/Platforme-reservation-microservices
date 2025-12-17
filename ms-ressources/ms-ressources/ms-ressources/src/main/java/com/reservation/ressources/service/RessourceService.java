package com.reservation.ressources.service;

import com.reservation.ressources.entity.Ressource;
import com.reservation.ressources.entity.TypeRessource;
import java.util.List;
import java.util.Optional;

public interface RessourceService {

    Ressource createRessource(Ressource ressource);
    Ressource updateRessource(Long id, Ressource ressource);
    void deleteRessource(Long id);
    Optional<Ressource> getRessourceById(Long id);
    List<Ressource> getAllRessources();
    List<Ressource> getRessourcesByType(TypeRessource type);
    List<Ressource> getRessourcesDisponibles();
    List<Ressource> searchRessources(TypeRessource type, Integer capaciteMin,
                                     Double prixMax, Boolean disponible);
    Ressource updateDisponibilite(Long id, Boolean disponible);
}