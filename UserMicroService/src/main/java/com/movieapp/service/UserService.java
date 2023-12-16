package com.movieapp.service;

import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.request.UpdateUserRequestDto;
import com.movieapp.exception.enums.FriendlyMessageCodes;
import com.movieapp.exception.exceptions.UserNotFoundException;
import com.movieapp.mapper.IUserMapper;
import com.movieapp.repository.IUserRepository;
import com.movieapp.repository.entity.User;
import com.movieapp.repository.enums.ELanguage;
import com.movieapp.response.UserResponse;
import com.movieapp.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService extends ServiceManager<User, String> {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(MongoRepository<User, String> repository, IUserRepository userRepository, IUserMapper userMapper) {
        super(repository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;

    }

    public User getUser(ELanguage language, String userId){
        User user = userRepository.getByUserId(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(language, FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION, "User not found for user id: " + userId);
        }
        return user;
    }


    public User saveUser(ELanguage language, CreateUserRequestDto createUserRequestDto){
        User user = userMapper.saveToUser(language, createUserRequestDto);
        return save(user);

    }
    public User updateUser(ELanguage language, String userId, UpdateUserRequestDto updateUserRequestDto){
        User user = getUser(language,userId);
        user.setName(updateUserRequestDto.getName());
        user.setSurname(updateUserRequestDto.getSurname());
        user.setEmail(updateUserRequestDto.getEmail());
        user.setPhoneNumber(updateUserRequestDto.getPhoneNumber());
        user.setPassword(updateUserRequestDto.getPassword());
        return save(user);
    }




}
