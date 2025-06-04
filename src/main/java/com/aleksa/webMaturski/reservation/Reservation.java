package com.aleksa.webMaturski.reservation;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Document(collection = "reservations")
public class Reservation {
    @Id
    private String Id;
    private String tableId;
    private LocalDateTime date;
    private String name;
}
