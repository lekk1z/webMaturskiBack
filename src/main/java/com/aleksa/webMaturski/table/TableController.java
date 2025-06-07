package com.aleksa.webMaturski.table;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private TableRepository tableRepository;

    @GetMapping
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    @GetMapping("/{id}")
    public Table getTableById(@PathVariable String id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
    }

    @PostMapping
    public ResponseEntity<?> createTable(@RequestBody Table table) {
        try {
            Table created = tableRepository.save(table);
            return ResponseEntity.ok(created);
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body("A table with this placement already exists.");
        }
    }
    @PutMapping("/{id}")
    public Table updateTable(@PathVariable String id, @RequestBody Table table) {
        if (!tableRepository.existsById(id)) {
            throw new RuntimeException("Table not found");
        }
        table.setId(id);
        return tableRepository.save(table);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable String id) {
        tableRepository.deleteById(id);
    }
}
