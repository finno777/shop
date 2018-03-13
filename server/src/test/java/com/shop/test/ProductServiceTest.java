package com.shop.test;

import com.shop.server.model.Comment;
import com.shop.server.model.Product;
import com.shop.server.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app.xml"})
@Transactional
public class ProductServiceTest {


    @Autowired
    ProductService productService;

    private Product getProduct(){
        List<Comment> comments=new ArrayList<>();
        Comment comment=new Comment(1L,"test");
        comments.add(comment);
        Product product=new Product(1L,"test",1L,comments);
        return product;
    }


    @Test
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
    @Test
    public void getAllCommentsByProduct(){
        List<Comment> commentByProduct = productService.getCommentByProduct(getProduct().getProductId());
        System.out.println(commentByProduct);
    }


}
