package com.example.postservice.repo;

import com.example.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepo extends MongoRepository<Post,String> {

}
