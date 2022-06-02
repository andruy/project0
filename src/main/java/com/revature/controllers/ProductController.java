package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.Role;
import com.revature.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    @GetMapping
    List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    @GetMapping("/{product_id}")
    Product getProductById(@PathVariable("product_id") int product_id) {
        return productRepository.findById(product_id).get();
    }

    //Code to delete
    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    @GetMapping("/{product_id}/orders")
    Set<Order> getOrders(@PathVariable("product_id") int product_id) {
        Product product = productRepository.findById(product_id).get();
        return product.getOrders();
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @PostMapping
    Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{product_id}")
    Product updateProduct(@PathVariable("product_id") int product_id, @RequestBody Product product) {
        Product productToUpdate = productRepository.findById(product_id).get();
        productToUpdate.setProduct_name(product.getProduct_name());
        productToUpdate.setProduct_price(product.getProduct_price());
        productToUpdate.setProduct_quantity(product.getProduct_quantity());
        return productRepository.save(productToUpdate);
    }

}
