package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "orders", schema = "project0")  // This tells Hibernate to make a table out of this class
@Data // This tells lombok to generate getters and setters for us
@AllArgsConstructor    // This is a constructor that takes in all the fields
@NoArgsConstructor      // No-args constructor is required for Hibernate
public class Order {


    @Id // This tells Hibernate to make a primary key out of this field
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // This tells Hibernate to auto-increment this field
    @Column(nullable = false, unique = true, updatable = false)
    private int order_id;  // This tells Hibernate to create an int field for the id column in the database
    private int user_id;
    private int order_amount;
    @Column(name = "purchaseDate", columnDefinition = "DATE DEFAULT CURRENT_DATE")  // This tells Hibernate to create
    // a varchar field for the name column in the database
    private java.sql.Date purchaseDate; // This tells Hibernate to create a double field for the price
    // column in the database

    @ManyToMany(mappedBy = "orders_product")
    Set<Product> products_order;

    public void addProduct(Product   product) {
        products_order.add(product);
    }

    public void assignUser(User user) {
        user_id = user.getUser_id();
    }
}
