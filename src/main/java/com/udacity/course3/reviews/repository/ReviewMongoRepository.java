package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMongoRepository extends MongoRepository<ReviewMongo, String> {
    @Query("{ '_id' : ?0 }")
    ReviewMongo findBy_id(Integer id);
}
