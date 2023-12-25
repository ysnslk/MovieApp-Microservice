package com.movieapp.repository;

import com.movieapp.repository.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepository extends MongoRepository<Auth, String> {
}
