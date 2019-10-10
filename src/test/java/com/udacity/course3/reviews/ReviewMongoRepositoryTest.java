package com.udacity.course3.reviews;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.ReviewMongo;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ReviewMongoRepositoryTest {
/*    @Autowired
    private ReviewMongoRepository reviewMongoRepository;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;*/

/*    private static Product product = new Product();

    public void productSetup() {
        product.setProduct_name("a new product");
        product.setProduct_desc("test test test");
        entityManager.persist(product);
        productRepository.save(product);
    }*/

    @Test
    public void createReviewWithComments(@Autowired MongoTemplate mongoTemplate) {
 /*       Product product = new Product();
        product.setProduct_name("a new product");
        product.setProduct_desc("test test test");*/
        ReviewMongo review = new ReviewMongo();
        review.setProductId("1");
        review.set_id(1);
        review.setReviewTitle("a review title");
        review.setReviewTxt("review text body");
        review.setReviewUsername("user xyz");
        review.setCreated(new Date());
        Comment comment1 = new Comment();
        comment1.setCommentTxt("a new piece of comment");
        comment1.setCreated(new Date());
        Comment comment2 = new Comment();
        comment2.setCommentTxt("a second piece of comment");
        comment2.setCreated(new Date());
        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments.add(comment1);
        comments.add(comment2);
        review.setComments(comments);
        // reviewMongoRepository.save(review);
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("_id", review.get_id())
                .add("productId", review.getProductId())
                .add("reviewUsername", review.getReviewUsername())
                .add("reviewTitle", review.getReviewTitle())
                .add("reviewTxt", review.getReviewTxt())
                .add("comments", review.getComments())
                .get();

        // when
        mongoTemplate.save(objectToSave, "reviews");

        // then
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1));
        assertThat(mongoTemplate.findOne(query, ReviewMongo.class))
                .extracting("productId").containsOnly("1");

    }


}
