package com.revature.controllers;

import com.revature.models.LoginTemplate;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation is used to tell Spring that this class is a controller
public class LoginController {
    @Autowired // This annotation tells Spring to inject the UserService object into this controller
    private UserService userService;    // This is the name of the field that will be injected

    @PostMapping("/login")  // This annotation tells Spring that this method will be mapped to the /login endpoint
    public ResponseEntity<User> login(@RequestBody LoginTemplate loginTemplate) {
        // This annotation tells Spring that this method will accept a LoginTemplate object as a parameter
        return ResponseEntity.ok(userService.login(loginTemplate.getUsername(), loginTemplate.getPassword()));

    }

    @PostMapping("/logout") // This annotation tells Spring that this method will be mapped to the /logout endpoint
    public ResponseEntity<Void> logout() {
        userService.logout();   // This method will call the logout method on the UserService object
        return ResponseEntity.accepted().build();   // This method will return an HTTP response with status code 202
    }
}
