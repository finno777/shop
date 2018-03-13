package com.shop.server.service.impl;

import com.shop.server.dao.CommentDao;
import com.shop.server.model.Comment;
import com.shop.server.model.Product;
import com.shop.server.service.CommentService;
import com.shop.server.service.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    ProductService productService;

    @Autowired
    CommentDao commentDao;

    @Override
    public void saveComment(Comment comment, Long id) {
        log.debug("get product by id");
        Product productById = productService.getProductById(id);
        comment.setProduct(productById);
        commentDao.saveComment(comment);
    }
}
