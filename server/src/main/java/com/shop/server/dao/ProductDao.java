package com.shop.server.dao;

import com.shop.server.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    void saveProduct(Product product);

    void deleteProduct(Long id);

}
