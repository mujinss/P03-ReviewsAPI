package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMongoRepository extends MongoRepository<ReviewMongo, String> {
    ReviewMongo findBy_id(String _id);
}
