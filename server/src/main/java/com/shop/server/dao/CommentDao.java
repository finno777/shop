package com.shop.server.dao;

import com.shop.server.model.Comment;
import com.shop.server.model.Product;
import java.util.List;

public interface CommentDao {

    void saveComment(Comment comment);

    List<Comment> getAllCommentByProduct(Product product);

}
