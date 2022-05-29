package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service    // This annotation tells Spring that this class is a service
public class UserService {

    @Autowired // This annotation tells Spring to inject the UserRepository object into this service
    private HttpServletRequest req; // This is used to get the session

    @Autowired
    private UserRepository userDAO; // This is the DAO object that we will use to interact with the database

    public List<User> findAll() {
        return userDAO.findAll();
    }  // This method returns all users in the database

    public User findById(int user_id) {
        return userDAO.findById(user_id)    // This method returns the user with the given ID
                .orElseThrow(() -> new UserNotFoundException(String.format("No user with id = id%", user_id)));
        // If the user doesn't exist, throw an exceptions
    } // This method returns a user with the given ID

    public User insert(User u) { // This method inserts a new user into the database
        if (u.getUser_id() != 0) {
            // This should be a custom exceptions class instead
            throw new RuntimeException("User ID must be zero to create a new User");
        }

        userDAO.save(u); // Modify the user with the new ID

        return u;
    } // This method inserts a new user into the database

    public User update(User u) { // This method updates an existing user in the database
        if (!userDAO.existsById(u.getUser_id())) {
            throw new RuntimeException("User must already exist to update");
        }

        userDAO.save(u); // Modify the user with the new ID

        HttpSession session = req.getSession(false); // They must have already been logged in, because we had our guard method

        User sessionUser = (User) session.getAttribute("currentUser");

        // If a User updated themselves, update the information in the session

        if (sessionUser.getUser_id() == u.getUser_id()) {
            session.setAttribute("currentUser", u);
        }

        return u;
    } // This method updates an existing user in the database

    public boolean delete(int user_id) {
        if (!userDAO.existsById(user_id)) {
            return false;
        }
        userDAO.deleteById(user_id);    // This method deletes a user from the database
        return true;
    }

    public User login(String username, String password) {
        User exists = userDAO.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("No User with username = %s", username)));

        if (!exists.getPassword().equals(password)) {
            throw new UserNotFoundException(String.format("No user with username = %s and password = %s", username, password));
        }
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", exists);
        return exists;
    }

    public void logout() {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return;
        }
        session.invalidate();
    }
}
