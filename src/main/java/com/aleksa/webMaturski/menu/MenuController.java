package com.aleksa.webMaturski.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    public List<Menu> getAllMenuItems(){
        return menuRepository.findAll();
    }
    // Get a specific menu item by ID
    @GetMapping("/{id}")
    public Menu getMenuItemById(@PathVariable String id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }
    @GetMapping("/category/{categoryId}")
    public List<Menu> getMenuItemsByCategoryId(@PathVariable int categoryId) {
        return menuRepository.findAllByCategoryId(categoryId);
    }
    // Create a new menu item
    @PostMapping
    public Menu createMenuItem(@RequestBody Menu menu) {
        return menuRepository.save(menu);
    }

    // Update an existing menu item
    @PutMapping("/{id}")
    public Menu updateMenuItem(@PathVariable String id, @RequestBody Menu menu) {
        if (!menuRepository.existsById(id)) {
            throw new RuntimeException("Menu item not found");
        }
        menu.setId(id);
        return menuRepository.save(menu);
    }

    // Delete a menu item
    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable String id) {
        menuRepository.deleteById(id);
    }
}
