package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Integer id;

    @NotBlank
    @Column(name="review_username")
    private String reviewUsername;

    @NotBlank
    @Column(name="review_title")
    private String reviewTitle;

    @NotBlank
    @Column(name="review_txt")
    private String reviewTxt;

    @Column(name="created", columnDefinition="timestamp default current_timestamp")
    private Date created;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReviewUsername() {
        return reviewUsername;
    }

    public void setReviewUsername(String reviewUsername) {
        this.reviewUsername = reviewUsername;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewTxt() {
        return reviewTxt;
    }

    public void setReviewTxt(String reviewTxt) {
        this.reviewTxt = reviewTxt;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
