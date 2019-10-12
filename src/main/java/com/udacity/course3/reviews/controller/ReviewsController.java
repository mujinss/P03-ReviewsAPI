package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewMongo;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewMongoRepository reviewMongoRepository;

    /**
     * Creates a review for a product.
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
            ReviewMongo review_to_save = new ReviewMongo();
            Review review_sql_saved = reviewRepository.save(review);
            review_to_save.set_id(review_sql_saved.getId());
            review_to_save.setProductId(productId.toString());
            review_to_save.setCreated(review_sql_saved.getCreated());
            review_to_save.setReviewUsername(review_sql_saved.getReviewUsername());
            review_to_save.setReviewTitle(review_sql_saved.getReviewTitle());
            review_to_save.setReviewTxt(review_sql_saved.getReviewTxt());
            reviewMongoRepository.save(review_to_save);
            return ResponseEntity.ok(review_sql_saved);
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
    public ResponseEntity<List<ReviewMongo>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> product = productRepository.findById(productId);

       ArrayList<ReviewMongo> res = new ArrayList<ReviewMongo>();
       if(product.isPresent()) {
           for (Review review : reviewRepository.findAllByProduct(product.get())) {
               res.add(reviewMongoRepository.findBy_id(review.getId()));
           }
           return ResponseEntity.ok(res);
       } else {
           return ResponseEntity.notFound().build();
       }
    }

}