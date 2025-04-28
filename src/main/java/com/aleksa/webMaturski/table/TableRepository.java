package com.aleksa.webMaturski.table;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TableRepository extends MongoRepository<Table, String> {
}
