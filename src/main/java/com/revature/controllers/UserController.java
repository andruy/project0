package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthorizationService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This is a controller that returns JSON
@RequestMapping("/users")   // This is the base URL for all of the methods in this controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Authorized(allowedRoles = {Role.ADMIN})
    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> findById(@PathVariable("user_id") int user_id) {
        authorizationService.guardByUserId(user_id);

        return ResponseEntity.ok(userService.findById(user_id));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User u) {

        return ResponseEntity.accepted().body(userService.insert(u));
    }


    @PutMapping
    @Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER, Role.EMPLOYEE})
    public ResponseEntity<User> update(@RequestBody User u) {
        authorizationService.guardByUserId(u.getUser_id());
        // We will also check if this resource belongs to the User, even if they pass the @Authorized annotation

        return ResponseEntity.accepted().body(userService.update(u));
    }

    @Authorized(allowedRoles = {Role.ADMIN})
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> delete(@PathVariable("user_id") int user_id) {
        if (userService.delete(user_id)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
