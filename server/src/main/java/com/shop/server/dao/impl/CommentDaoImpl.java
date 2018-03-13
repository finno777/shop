package com.shop.server.dao.impl;

import com.shop.server.dao.CommentDao;
import com.shop.server.model.Comment;
import com.shop.server.model.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveComment(Comment comment) {
        getSession().saveOrUpdate(comment);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> getAllCommentByProduct(Product product) {
        Criteria criteria = getSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("product", product));
        return criteria.list();
    }
}
