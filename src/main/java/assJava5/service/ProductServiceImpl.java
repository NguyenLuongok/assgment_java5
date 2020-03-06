package assJava5.service;


import assJava5.model.Products;
import assJava5.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products findById(Long id) {
        return productsRepository.findById(id);
    }


    @Override
    public void save(Products products) {
        productsRepository.save(products);
    }

    @Override
    public void refresh(Products products) {
        productsRepository.refresh(products);
    }

    @Override
    public void remove(Long id) {
        productsRepository.remove(id);
    }

    @Override
    public Products findByName(String name) {
        return productsRepository.findByName(name);
    }

    @Override
    public void update(Long id, Products products) {
        productsRepository.update(id, products);
    }

    @Override
    public List<Products> findByNameSession(String name) {
        return productsRepository.findByNameSession(name);
    }
}
