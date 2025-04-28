package com.aleksa.webMaturski.order;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import com.aleksa.webMaturski.order.OrderItem;
@Data
@Document( collection = "orders")
public class Order {
    @Id
    private String id;
    private List<OrderItem> items;

}
