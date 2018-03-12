package com.shop.server.dao;

import com.shop.server.model.Comment;
import com.shop.server.model.Product;

import java.util.List;

public interface CommentDao {

    Comment getCommentById(Long id);

    List<Comment> getAllComments();

    void saveComment(Comment comment);

    void deleteComment(Long id);

    List<Comment> getAllCommentByProduct(Product product);
}
