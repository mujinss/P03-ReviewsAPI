package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
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
public class CommentRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @Test
    public void testCreateCommentForReview() {
        Review review = new Review();
        Comment comment = new Comment();
        Product prod = new Product();
        prod.setProduct_name("coffee maker");

        comment.setCommentTxt("a new piece of comment");
        comment.setCreated(new Date());
        review.setReviewTitle("a review title");
        review.setReviewTxt("review text body");
        review.setReviewUsername("user xyz");
        review.setCreated(new Date());
        review.setProduct(prod);
        comment.setReview(review);

        entityManager.persist(prod);
        entityManager.persist(review);
        entityManager.persist(comment);

        commentRepository.save(comment);
        Optional<Comment> actualComment = commentRepository.findById(1);
        assertThat(actualComment).isNotNull();
        assertEquals(comment.getCommentTxt(), actualComment.get().getCommentTxt());
        assertEquals(comment.getReview(), actualComment.get().getReview());
    }

    @Test
    public void testListAllCommentForReview() {
        Product prod = new Product();
        prod.setProduct_name("coffee maker");

        Review review= new Review();
        review.setReviewTitle("a review title");
        review.setReviewTxt("review text body");
        review.setReviewUsername("user xyz");
        review.setCreated(new Date());
        review.setProduct(prod);

        Comment comment1 = new Comment();
        comment1.setReview(review);
        comment1.setCommentTxt("comment 1 text");
        comment1.setCreated(new Date());

        Comment comment2 = new Comment();
        comment2.setCommentTxt("comment 2 text");
        comment2.setCreated(new Date());
        comment2.setReview(review);

        entityManager.persist(prod);
        entityManager.persist(review);
        entityManager.persist(comment1);
        entityManager.persist(comment2);

        List<Comment> commentList = commentRepository.findAllByReview(review);
        assertEquals(commentList.size(), 2);
    }

}
