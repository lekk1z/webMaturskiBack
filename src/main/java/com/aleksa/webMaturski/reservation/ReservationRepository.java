package com.aleksa.webMaturski.reservation;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByDate(LocalDateTime date);
    List<Reservation> findByTableId(String tableId);
    List<Reservation> findByTableIdAndDate(String tableId, LocalDateTime date); // optional combined filter
}
