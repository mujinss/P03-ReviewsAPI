package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Review> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                         @RequestBody Review review) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            review.setProduct(product.get());
            review.setCreated(new Date());
            return ResponseEntity.ok(reviewRepository.save(review));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            return ResponseEntity.ok(reviewRepository.findAllByProduct(product.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}