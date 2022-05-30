package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users", schema = "project0") // This tells Hibernate to make a table out of this class
@Data // This tells lombok to generate getters and setters for us
@AllArgsConstructor // This is a constructor that takes in all the fields
@NoArgsConstructor // No-args constructor is required for Hibernate
public class User {
    @Id     // This tells Hibernate to make a primary key out of this field
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // This tells Hibernate to auto-increment this field
    @Column(nullable = false, unique = true, updatable = false)
    // This tells Hibernate to create an int field for the id column in the database
    private int user_id;   // This tells Hibernate to create an int field for the id column in the database
    private String username;  // This tells Hibernate to create a varchar field for the username column in the database
    private String password;  // This tells Hibernate to create a varchar field for the password column in the database
    @NotEmpty(message = "Please enter a valid email")
    @Email
    private String email; // This tells Hibernate to create a varchar field for the name column in the database
    private String firstname; // This tells Hibernate to create a varchar field for the firstname column in the database
    private String lastname;  // This tells Hibernate to create a varchar field for the lastname column in the database
    private Role role;

    //One-to-many relationship.
    @OneToMany(cascade = CascadeType.ALL)
    // This tells Hibernate to create a one-to-many relationship with the cart table
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // This tells Hibernate to create a foreign key
    // in the cart table that references the user_id column in the user table
    private List<Order> order;  // This tells Hibernate to create a list of orders for this user

    //Getter and Setter for the user's role
    public int getUser_id() { //This is the getter for the user_id
        return user_id; //This returns the user_id
    }

    public void setUser_id(int user_id) {  //This is the setter for the user_id
        this.user_id = user_id; //This sets the user_id to the user_id passed in
    }

    public String getUsername() {   //This is the getter for the username
        return username;    //This returns the username
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Order> getOrder() { //This is the getter for the order
        return order; //This returns the order
    }

    public void setOrder(List<Order> order) { //This is the setter for the order
        this.order = order; //This sets the order to the order passed in
    }


}
