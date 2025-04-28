package com.aleksa.webMaturski.menu;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<Menu, String> {
    List<Menu> findAllByCategoryId(int categoryId);
}
