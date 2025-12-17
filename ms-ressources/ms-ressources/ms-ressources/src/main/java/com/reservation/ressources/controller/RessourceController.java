package com.reservation.ressources.controller;

import com.reservation.ressources.entity.Ressource;
import com.reservation.ressources.entity.TypeRessource;
import com.reservation.ressources.service.RessourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ressources")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class RessourceController {

    private final RessourceService ressourceService;

    @GetMapping
    public ResponseEntity<List<Ressource>> getAllRessources() {
        log.info("GET /ressources - Récupération de toutes les ressources");
        return ResponseEntity.ok(ressourceService.getAllRessources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ressource> getRessourceById(@PathVariable Long id) {
        log.info("GET /ressources/{} - Récupération d'une ressource", id);
        return ressourceService.getRessourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ressource> createRessource(@Valid @RequestBody Ressource ressource) {
        log.info("POST /ressources - Création d'une ressource: {}", ressource.getNom());
        Ressource created = ressourceService.createRessource(ressource);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ressource> updateRessource(
            @PathVariable Long id,
            @Valid @RequestBody Ressource ressource) {
        log.info("PUT /ressources/{} - Mise à jour d'une ressource", id);
        try {
            Ressource updated = ressourceService.updateRessource(id, ressource);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
        log.info("DELETE /ressources/{} - Suppression d'une ressource", id);
        ressourceService.deleteRessource(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Ressource>> getRessourcesDisponibles() {
        log.info("GET /ressources/disponibles");
        return ResponseEntity.ok(ressourceService.getRessourcesDisponibles());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Ressource>> getRessourcesByType(@PathVariable TypeRessource type) {
        log.info("GET /ressources/type/{}", type);
        return ResponseEntity.ok(ressourceService.getRessourcesByType(type));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Ressource>> searchRessources(
            @RequestParam(required = false) TypeRessource type,
            @RequestParam(required = false) Integer capaciteMin,
            @RequestParam(required = false) Double prixMax,
            @RequestParam(required = false) Boolean disponible) {
        log.info("GET /ressources/search - type:{}, cap:{}, prix:{}, dispo:{}",
                type, capaciteMin, prixMax, disponible);
        return ResponseEntity.ok(ressourceService.searchRessources(type, capaciteMin, prixMax, disponible));
    }

    @PatchMapping("/{id}/disponibilite")
    public ResponseEntity<Ressource> updateDisponibilite(
            @PathVariable Long id,
            @RequestParam Boolean disponible) {
        log.info("PATCH /ressources/{}/disponibilite - {}", id, disponible);
        try {
            Ressource updated = ressourceService.updateDisponibilite(id, disponible);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}