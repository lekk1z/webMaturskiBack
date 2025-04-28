package com.aleksa.webMaturski.order;

import lombok.Data;

@Data
public class OrderItem {
    private String menuItemId;
    private int quantity;
}
