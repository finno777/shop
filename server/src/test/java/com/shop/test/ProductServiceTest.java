package com.shop.test;

import com.shop.server.model.Product;
import com.shop.server.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app.xml"})
@Transactional
public class ProductServiceTest {


    @Autowired
    ProductService productService;

    private Product getProduct(){
        Product product=new Product(1L,"test",1L);
        return product;
    }


    @Test
    @Rollback
    public  void addProductTest(){
        Product product=getProduct();
        System.out.println("save");
        productService.saveProduct(product);
    }

    @Test
    public void deleteProductTest(){
        Product productByDelete=getProduct();
        System.out.println("delete");
        productService.deleteProductById(productByDelete.getProductId());
    }


}
