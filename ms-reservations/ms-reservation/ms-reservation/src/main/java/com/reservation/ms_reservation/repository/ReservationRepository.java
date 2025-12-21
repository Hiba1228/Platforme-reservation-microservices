package com.reservation.ms_reservation.repository;

import com.reservation.ms_reservation.entity.Reservation;
import com.reservation.ms_reservation.entity.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRessourceId(Long ressourceId);
    List<Reservation> findByClientEmail(String clientEmail);
    List<Reservation> findByStatut(StatutReservation statut);

    @Query("SELECT r FROM Reservation r WHERE r.ressourceId = :ressourceId " +
            "AND r.statut != 'CANCELLED' " +
            "AND ((r.dateDebut <= :dateFin AND r.dateFin >= :dateDebut))")
    List<Reservation> findConflictingReservations(
            @Param("ressourceId") Long ressourceId,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin);

    @Query("SELECT r FROM Reservation r WHERE r.dateCreation >= :dateDebut ORDER BY r.dateCreation DESC")
    List<Reservation> findRecentReservations(@Param("dateDebut") LocalDateTime dateDebut);
}