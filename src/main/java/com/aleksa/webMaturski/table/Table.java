package com.aleksa.webMaturski.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tables")
public class Table {
    @Id
    private String id;
    private String number;

    @Indexed(unique = true)
    private int placement;

    private String[] ordersId;
    private boolean occupied = false;
}
