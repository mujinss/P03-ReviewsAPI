package com.udacity.course3.reviews.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Document("reviews")
public class ReviewMongo {
    @Id
    private Integer _id;
    private String productId;

    private String reviewUsername;

    private String reviewTitle;

    private String reviewTxt;

    private Date created;

    private ArrayList<Comment> comments;

    public ReviewMongo() {}

    public ReviewMongo(String productId, String reviewUsername, String reviewTitle,
                       String reviewTxt, Date created, ArrayList<Comment> comments) {
        this.productId = productId;
        this.reviewUsername = reviewUsername;
        this.reviewTitle = reviewTitle;
        this.reviewTxt = reviewTxt;
        this.created = created;
        this.comments = comments;
    }

    public String getReviewUsername() {
        return reviewUsername;
    }

    public void setReviewUsername(String reviewUsername) {
        this.reviewUsername = reviewUsername;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
}
