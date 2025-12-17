package com.reservation.ressources.service;

import com.reservation.ressources.entity.Ressource;
import com.reservation.ressources.entity.TypeRessource;
import com.reservation.ressources.repository.RessourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RessourceServiceImpl implements RessourceService {

    private final RessourceRepository ressourceRepository;

    @Override
    public Ressource createRessource(Ressource ressource) {
        log.info("Création d'une nouvelle ressource: {}", ressource.getNom());
        return ressourceRepository.save(ressource);
    }

    @Override
    public Ressource updateRessource(Long id, Ressource ressource) {
        log.info("Mise à jour de la ressource ID: {}", id);
        return ressourceRepository.findById(id)
                .map(existing -> {
                    existing.setNom(ressource.getNom());
                    existing.setType(ressource.getType());
                    existing.setCapacite(ressource.getCapacite());
                    existing.setPrixParJour(ressource.getPrixParJour());
                    existing.setDescription(ressource.getDescription());
                    existing.setAdresse(ressource.getAdresse());
                    existing.setEquipements(ressource.getEquipements());
                    existing.setPhotoUrl(ressource.getPhotoUrl());
                    existing.setDisponible(ressource.getDisponible());
                    return ressourceRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Ressource non trouvée avec l'ID: " + id));
    }

    @Override
    public void deleteRessource(Long id) {
        log.info("Suppression de la ressource ID: {}", id);
        ressourceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ressource> getRessourceById(Long id) {
        return ressourceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ressource> getAllRessources() {
        return ressourceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ressource> getRessourcesByType(TypeRessource type) {
        return ressourceRepository.findByType(type);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ressource> getRessourcesDisponibles() {
        return ressourceRepository.findByDisponibleTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ressource> searchRessources(TypeRessource type, Integer capaciteMin,
                                            Double prixMax, Boolean disponible) {
        return ressourceRepository.searchRessources(type, capaciteMin, prixMax, disponible);
    }

    @Override
    public Ressource updateDisponibilite(Long id, Boolean disponible) {
        log.info("Mise à jour disponibilité ressource ID {}: {}", id, disponible);
        return ressourceRepository.findById(id)
                .map(ressource -> {
                    ressource.setDisponible(disponible);
                    return ressourceRepository.save(ressource);
                })
                .orElseThrow(() -> new RuntimeException("Ressource non trouvée avec l'ID: " + id));
    }
}