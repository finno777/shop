package com.shop.test;


import com.shop.server.model.Comment;
import com.shop.server.model.Product;
import com.shop.server.service.CommentService;
import com.shop.server.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app.xml"})

public class CommentServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    CommentService commentService;


    private Product getProduct(){
        Product product=new Product(1L,"test",1L);
        return product;
    }

    private Comment getComment(){
        Comment comment = new Comment(3L,"testsComment");
        return comment;
    }

    @Test
    public void test(){

    }


}
