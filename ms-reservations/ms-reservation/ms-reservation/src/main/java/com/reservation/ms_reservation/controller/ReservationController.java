package com.reservation.ms_reservation.controller;

import com.reservation.ms_reservation.config.ReservationConfig;
import com.reservation.ms_reservation.entity.Reservation;
import com.reservation.ms_reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReservationController {

    private final ReservationService service;
    private final ReservationConfig config;

    @GetMapping
    public List<Reservation> getAll() {
        return service.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        return service.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return service.createReservation(reservation);
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecent() {
        List<Reservation> reservations = service.getRecentReservations();
        return ResponseEntity.ok(Map.of(
                "message", "Réservations des " + config.getReservationsLast() + " derniers jours",
                "count", reservations.size(),
                "reservations", reservations
        ));
    }
}