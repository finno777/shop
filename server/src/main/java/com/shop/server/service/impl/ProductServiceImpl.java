package com.shop.server.service.impl;

import com.shop.server.dao.ProductDao;
import com.shop.server.model.Product;
import com.shop.server.service.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(Long id) {
            return productDao.getProductById(id);
    }

    @Override
    public void saveProduct(Product product) {
        log.debug("Save product");
        productDao.saveProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void deleteProductById(Long id) {
        if(id!=null){
            log.debug("Delete product");
            productDao.deleteProduct(id);
        }
    }
}
