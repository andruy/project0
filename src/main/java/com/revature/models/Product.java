package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "products", schema = "project0") // This tells Hibernate to make a table out of this class
@Data   // This tells lombok to generate getters and setters for us
@AllArgsConstructor // This is a constructor that takes in all the fields
@NoArgsConstructor  // No-args constructor is required for Hibernate
public class Product {

    @Id // This tells Hibernate to make a primary key out of this field
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // This tells Hibernate to auto-increment this field
    @Column(nullable = false, unique = true, updatable = false)
    private int product_id;    // This tells Hibernate to create an int field for the id column in the database
    private String product_name;  // This tells Hibernate to create a varchar field for the name column in the database
    private double product_price; // This tells Hibernate to create a double field for the price column in the database
    private int product_quantity; // This tells Hibernate to create an int field for the quantity column in the database

    @ManyToMany
    @JoinTable(name = "order_product", schema = "project0", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    Set<Order> orders_product;
}