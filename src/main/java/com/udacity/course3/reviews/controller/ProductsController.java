package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.of(productRepository.findById(id));
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }
}