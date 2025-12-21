package com.reservation.ms_reservation.service;

import com.reservation.ms_reservation.entity.Reservation;
import com.reservation.ms_reservation.entity.StatutReservation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Optional<Reservation> getReservationById(Long id);
    List<Reservation> getAllReservations();
    List<Reservation> getRecentReservations();
    Reservation updateStatut(Long id, StatutReservation statut);
}