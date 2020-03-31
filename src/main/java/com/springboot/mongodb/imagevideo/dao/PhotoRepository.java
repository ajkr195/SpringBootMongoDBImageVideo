package com.springboot.mongodb.imagevideo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.mongodb.imagevideo.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
