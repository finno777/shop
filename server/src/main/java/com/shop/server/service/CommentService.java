package com.shop.server.service;

import com.shop.server.model.Comment;
import com.shop.server.model.Product;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment, Long id);

}
