package com.movieapp.service;

import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.mapper.IUserMapper;
import com.movieapp.repository.IUserRepository;
import com.movieapp.repository.entity.User;
import com.movieapp.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceManager<User, String> {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(MongoRepository<User, String> repository, IUserRepository userRepository, IUserMapper userMapper) {
        super(repository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public User saveUser(CreateUserRequestDto createUserRequestDto){
        User user = userMapper.saveToUser(createUserRequestDto);
        return save(user);
    }
}
