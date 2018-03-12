package com.shop.server.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name",length = 100)
    private String name;
    @Column(name = "product_price", length = 5)
    private Long price;


    @Transient
    private List<Comment> comments;



    public Product(long l, String test, long l1) {
        this.productId=l;
        this.name=test;
        this.price=l1;
    }
}
