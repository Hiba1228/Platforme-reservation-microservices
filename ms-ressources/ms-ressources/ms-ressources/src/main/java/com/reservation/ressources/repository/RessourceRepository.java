package com.reservation.ressources.repository;

import com.reservation.ressources.entity.Ressource;
import com.reservation.ressources.entity.TypeRessource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {

    List<Ressource> findByType(TypeRessource type);

    List<Ressource> findByDisponibleTrue();

    List<Ressource> findByTypeAndDisponibleTrue(TypeRessource type);

    List<Ressource> findByCapaciteGreaterThanEqual(Integer capacite);

    List<Ressource> findByPrixParJourLessThanEqual(Double prix);

    @Query("SELECT r FROM Ressource r WHERE " +
            "(:type IS NULL OR r.type = :type) AND " +
            "(:capaciteMin IS NULL OR r.capacite >= :capaciteMin) AND " +
            "(:prixMax IS NULL OR r.prixParJour <= :prixMax) AND " +
            "(:disponible IS NULL OR r.disponible = :disponible)")
    List<Ressource> searchRessources(
            @Param("type") TypeRessource type,
            @Param("capaciteMin") Integer capaciteMin,
            @Param("prixMax") Double prixMax,
            @Param("disponible") Boolean disponible
    );
}