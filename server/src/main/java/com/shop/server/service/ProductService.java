package com.shop.server.service;

import com.shop.server.model.Comment;
import com.shop.server.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    void saveProduct(Product product);

    List<Product> getAllProducts();

    void deleteProductById(Long id);

    List<Comment> getCommentByProduct(Long id);

}
