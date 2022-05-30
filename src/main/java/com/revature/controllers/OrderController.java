package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    @GetMapping
    List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    @GetMapping("/{order_id}")
    Optional<Order> getOrderById(@PathVariable("order_id") int order_id) {
        return orderRepository.findById(order_id);
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER, Role.EMPLOYEE})
    @PostMapping
    Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER, Role.EMPLOYEE})
    @PutMapping("/{order_id}/products/{product_id}")
    Order addProductToOrder(
            @PathVariable("order_id") int order_id,
            @PathVariable("product_id") int product_id) {
        Order order = orderRepository.findById(order_id).get();
        Product product = productRepository.findById(product_id).get();
        order.addProduct(product);
        return orderRepository.save(order);
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER, Role.EMPLOYEE})
    @PutMapping("/{order_id}/products/{product_id}/quantity/{quantity}")
    Order addProductToOrder(
            @PathVariable("order_id") int order_id,
            @PathVariable("product_id") int product_id,
            @PathVariable("quantity") int quantity) {
        Order order = orderRepository.findById(order_id).get();
        Product product = productRepository.findById(product_id).get();
        order.addProduct(product);
        return orderRepository.save(order);
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER, Role.EMPLOYEE})
    @PutMapping("/{order_id}/users/{user_id}")
    Order addUserToOrder(
            @PathVariable("order_id") int order_id,
            @PathVariable("user_id") int user_id) {
        Order order = orderRepository.findById(order_id).get();
        User user = userRepository.findById(user_id).get();
        order.assignUser(user);
        return orderRepository.save(order);
    }
}
