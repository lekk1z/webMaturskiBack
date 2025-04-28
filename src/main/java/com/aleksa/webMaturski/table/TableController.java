package com.aleksa.webMaturski.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private TableRepository tableRepository;

    // Get all tables
    @GetMapping
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    // Get a specific table by ID
    @GetMapping("/{id}")
    public Table getTableById(@PathVariable String id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
    }

    // Create a new table
    @PostMapping
    public Table createTable(@RequestBody Table table) {
        return tableRepository.save(table);
    }

    // Update an existing table
    @PutMapping("/{id}")
    public Table updateTable(@PathVariable String id, @RequestBody Table table) {
        if (!tableRepository.existsById(id)) {
            throw new RuntimeException("Table not found");
        }
        table.setId(id);
        return tableRepository.save(table);
    }

    // Delete a table
    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable String id) {
        tableRepository.deleteById(id);
    }
}
