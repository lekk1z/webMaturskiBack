package com.aleksa.webMaturski.order;
import com.aleksa.webMaturski.menu.Menu;
import com.aleksa.webMaturski.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/orders")
public class OrderController {

        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private MenuRepository menuRepository;

        @GetMapping
        public List<Order> getAllOrders() {
            return orderRepository.findAll();
        }

        @GetMapping("/{id}")
        public Order getOrderById(@PathVariable String id) {
            return orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
        }

        @PostMapping
        public Order createOrder(@RequestBody Order order) {
            for (OrderItem item : order.getItems()) {
                if (!menuRepository.existsById(item.getMenuItemId())) {
                    throw new RuntimeException("Menu item not found: " + item.getMenuItemId());
                }
            }
            return orderRepository.save(order);
        }

        @PutMapping("/{id}")
        public Order updateOrder(@PathVariable String id, @RequestBody Order order) {
            if (!orderRepository.existsById(id)) {
                throw new RuntimeException("Order not found");
            }
            order.setId(id);
            return orderRepository.save(order);
        }

        @DeleteMapping("/{id}")
        public void deleteOrder(@PathVariable String id) {
            orderRepository.deleteById(id);
        }

        @DeleteMapping("/all")
        public void deleteAllOrders(){
            orderRepository.deleteAll();
        }

        @GetMapping("/{id}/total")
        public double calculateTotalPrice(@PathVariable String id) {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            double total = 0.0;
            for (OrderItem item : order.getItems()) {
                Menu menuItem = menuRepository.findById(item.getMenuItemId())
                        .orElseThrow(() -> new RuntimeException("Menu item not found"));
                total += menuItem.getPrice() * item.getQuantity();
            }
            return total;
        }
}
