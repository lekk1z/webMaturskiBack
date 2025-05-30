package com.aleksa.webMaturski.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tables")
public class Table {
    @Id
    private String id;
    private String number;
    private String[] ordersId;
    private boolean occupied = false;
}
