package assJava5.service;

import assJava5.model.Products;

import java.util.List;

public interface ProductsService {
    List<Products> findAll();
    Products findById(Long id);
    void save(Products products);
    void refresh(Products products);
    void update(Long id, Products products);
    void remove(Long id);
    Products findByName(String name);
    List<Products> findByNameSession(String name);
}
