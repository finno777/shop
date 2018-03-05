package com.shop.server.server;

import com.shop.server.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    void saveProduct(Product product);

    List<Product> getAllProducts();

    void deleteProductById(Long id);

}
