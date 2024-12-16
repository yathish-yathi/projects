package com.yathish.jobListing;

import com.yathish.jobListing.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
