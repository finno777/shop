package com.shop.server.dao.impl;

import com.shop.server.dao.ProductDao;
import com.shop.server.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }


    @SuppressWarnings("unchecked")
    @Override
    public Product getProductById(Long id) {
        return (Product) getSession().get(Product.class, id);
    }

    @Override
    public List<Product> getAllProducts() {
        return getSession().createCriteria(Product.class).list();
    }

    @Override
    public void saveProduct(Product product) {
        getSession().saveOrUpdate(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product=getProductById(id);
        if (product!=null){
            getSession().delete(product);
        }
    }


}
