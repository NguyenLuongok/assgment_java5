package assJava5.repository;

import assJava5.model.Products;

import java.util.List;

public interface ProductsRepository extends Repository<Products>{
    Products findByName(String name);
    List<Products> findByNameSession(String name);
    void refresh(Products products);
}
