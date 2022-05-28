package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users", schema = "projectzero") // This tells Hibernate to make a table out of this class
@Data // This tells lombok to generate getters and setters for us
@AllArgsConstructor // This is a constructor that takes in all the fields
@NoArgsConstructor // No-args constructor is required for Hibernate
public class User {
    @Id     // This tells Hibernate to make a primary key out of this field
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // This tells Hibernate to auto-increment this field
    @Column(nullable = false, unique = true, updatable = false)     // This tells Hibernate to create an int field for the id column in the database
    private int id;   // This tells Hibernate to create an int field for the id column in the database
    private String username;  // This tells Hibernate to create a varchar field for the username column in the database
    private String password;  // This tells Hibernate to create a varchar field for the password column in the database
    @NotEmpty(message = "Please enter a valid email")
    @Email
    private String email; // This tells Hibernate to create a varchar field for the name column in the database
    private String firstname; // This tells Hibernate to create a varchar field for the firstname column in the database
    private String lastname;  // This tells Hibernate to create a varchar field for the lastname column in the database

    @OneToMany  // This tells Hibernate to create a one-to-many relationship with the Item table
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // This tells Hibernate to create a foreign key in
    // the Item table that references the id column in the User table
    private List<Cart> cart; // This tells Hibernate to create a list of Item objects that belong to this User
//    private Cart cart;  // This tells Hibernate to create a one-to-one relationship with the Cart table

}
