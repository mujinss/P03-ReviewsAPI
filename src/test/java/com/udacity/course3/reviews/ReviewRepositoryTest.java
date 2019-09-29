package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void testCreateReviewForProduct() {
        Review review = new Review();
        Product product = new Product();

        product.setProduct_name("a new product");
        product.setProduct_desc("test test test");
        review.setProduct(product);
        review.setReviewTitle("a review title");
        review.setReviewTxt("review text body");
        review.setReviewUsername("user xyz");
        review.setCreated(new Date());

        entityManager.persist(product);
        entityManager.persist(review);
        productRepository.save(product);
        reviewRepository.save(review);
        Optional<Review> actualReview = reviewRepository.findById(1);
        assertThat(actualReview).isNotNull();

    }

    @Test
    public void testListAllReviewForProduct() {
        Product prod = new Product();
        prod.setProduct_name("coffee maker");

        Review review1= new Review();
        review1.setProduct(prod);
        review1.setReviewTitle("a review title");
        review1.setReviewTxt("review text body");
        review1.setReviewUsername("user xyz");
        review1.setCreated(new Date());

        Review review2 = new Review();
        review2.setProduct(prod);
        review2.setReviewUsername("user abc");
        review2.setReviewTxt("hello");
        review2.setReviewTitle("world");
        review2.setCreated(new Date());


        entityManager.persist(prod);
        entityManager.persist(review1);
        entityManager.persist(review2);

        List<Review> reviewList = reviewRepository.findAllByProduct(prod);
        assertEquals(reviewList.size(), 2);
    }
}
