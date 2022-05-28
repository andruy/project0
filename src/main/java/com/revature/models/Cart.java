package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "carts", schema = "projectzero")  // This tells Hibernate to make a table out of this class
@Data // This tells lombok to generate getters and setters for us
@AllArgsConstructor    // This is a constructor that takes in all the fields
@NoArgsConstructor      // No-args constructor is required for Hibernate
public class Cart {

    @Id // This tells Hibernate to make a primary key out of this field
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // This tells Hibernate to auto-increment this field
    @Column(nullable = false, unique = true, updatable = false)
    private int id;  // This tells Hibernate to create an int field for the id column in the database
    private int item_id;
    private int user_id;

}
