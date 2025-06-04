package com.aleksa.webMaturski.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    // Get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Get a reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new reservation
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Update an existing reservation
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation updatedReservation) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setTableId(updatedReservation.getTableId());
                    reservation.setDate(updatedReservation.getDate());
                    reservation.setName(updatedReservation.getName());
                    return ResponseEntity.ok(reservationRepository.save(reservation));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Filter reservations by date
    @GetMapping("/by-date")
    public List<Reservation> getReservationsByDate(@RequestParam("date") LocalDateTime date) {
        return reservationRepository.findByDate(date);
    }

    // Filter reservations by table ID
    @GetMapping("/by-table")
    public List<Reservation> getReservationsByTable(@RequestParam("tableId") String tableId) {
        return reservationRepository.findByTableId(tableId);
    }

    // Optional: Filter by both table ID and date
    @GetMapping("/by-table-and-date")
    public List<Reservation> getReservationsByTableAndDate(@RequestParam("tableId") String tableId,
                                                           @RequestParam("date") LocalDateTime date) {
        return reservationRepository.findByTableIdAndDate(tableId, date);
    }

}
