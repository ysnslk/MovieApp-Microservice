package com.movieapp.repository;

import com.movieapp.dto.request.UpdateUserRequestDto;
import com.movieapp.repository.entity.User;
import com.movieapp.repository.enums.ELanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {
    User getByUserId(String userId);
}
