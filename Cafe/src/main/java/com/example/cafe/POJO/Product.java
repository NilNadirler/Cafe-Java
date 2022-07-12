package com.example.cafe.POJO;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name ="Product.getAllProduct", query = "select new com.example.cafe.wrapper.ProductWrapper(p.id, p.name, p.description,p.price,p.status,p.category.id,p.category.name) from Product p")
@NamedQuery(name = "Product.updateProductStatus", query = "update Product p set p.status=:status where p.id=:id")
@NamedQuery(name = "Product.getProductByCategory", query = "select new com.example.cafe.wrapper.ProductWrapper(p.id,p.name) from Product p where p.category.id=:id and p.status='true'")
@NamedQuery(name = "Product.getProductById", query = "select new com.example.cafe.wrapper.ProductWrapper(p.id, p.name, p.description,p.price) from Product p where p.category.id=:id")

@Entity
@Table(name = "products")
@Data
@DynamicUpdate
@DynamicInsert
public class Product implements Serializable {

    public static final Long serialVersionUid = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    private String description;

    private Integer price;

    private String status;


}
