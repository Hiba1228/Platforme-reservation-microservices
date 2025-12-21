package com.reservation.ms_reservation.service;

import com.reservation.ms_reservation.client.RessourceClient;
import com.reservation.ms_reservation.config.ReservationConfig;
import com.reservation.ms_reservation.dto.RessourceDTO;
import com.reservation.ms_reservation.entity.Reservation;
import com.reservation.ms_reservation.entity.StatutReservation;
import com.reservation.ms_reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final RessourceClient ressourceClient;
    private final ReservationConfig config;

    @Override
    public Reservation createReservation(Reservation reservation) {
        RessourceDTO ressource = ressourceClient.getRessourceById(reservation.getRessourceId());
        if (ressource == null || !ressource.getDisponible()) {
            throw new RuntimeException("Ressource non disponible");
        }

        long jours = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
        reservation.setMontantTotal(jours * ressource.getPrixParJour());

        return repository.save(reservation);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    @Override
    public List<Reservation> getRecentReservations() {
        Integer days = config.getReservationsLast();
        LocalDateTime date = LocalDateTime.now().minusDays(days);
        return repository.findRecentReservations(date);
    }

    @Override
    public Reservation updateStatut(Long id, StatutReservation statut) {
        return repository.findById(id)
                .map(r -> {
                    r.setStatut(statut);
                    return repository.save(r);
                })
                .orElseThrow();
    }
}