package com.movieapp.service;

import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.request.UpdateUserRequestDto;
import com.movieapp.exception.enums.FriendlyMessageCodes;
import com.movieapp.exception.exceptions.*;
import com.movieapp.mapper.IUserMapper;
import com.movieapp.repository.IUserRepository;
import com.movieapp.repository.entity.User;
import com.movieapp.repository.enums.ELanguage;
import com.movieapp.repository.enums.EStatus;
import com.movieapp.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User, String> {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(MongoRepository<User, String> repository, IUserRepository userRepository, IUserMapper userMapper) {
        super(repository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;

    }

    public User saveUser(ELanguage language, CreateUserRequestDto createUserRequestDto) {
        try {
            User user = userMapper.saveToUser(createUserRequestDto);
            return save(user);
        }catch (Exception e){
            throw new UserNotCreatedException(language,FriendlyMessageCodes.USER_NOT_CREATED_EXCEPTION, "User not created");
        }
    }

    public User updateUser(ELanguage language, UpdateUserRequestDto updateUserRequestDto) {
        Optional<User> user = findById(updateUserRequestDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(language, FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION,
                    "User not found for user id: " + updateUserRequestDto.getUserId());
        }
        try {
            user = Optional.ofNullable(userMapper.updateToUser(updateUserRequestDto));
        }
        catch (Exception e){
            throw new UserUpdateFailed(language, FriendlyMessageCodes.USER_UPDATE_FAILED,
                    "User update failed for user id: " + updateUserRequestDto.getUserId());
        }

        return update(user.get());
    }

    public List<User> findAllUser() {
        return findAll();
    }

    public boolean deleteUser(ELanguage language, String userId) {
        Optional<User> user = findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(language, FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION,
                    "User not found for user id: " + userId);
        }
        if (user.get().getStatus()== EStatus.DELETED){
            throw new UserAlreadyDeletedException(language,FriendlyMessageCodes.USER_ALREADY_DELETED,
                    "User already deleted for user id: " +userId);
        }
        user.get().setStatus(EStatus.DELETED);

        try {
            update(user.get());
        } catch (Exception e) {
            throw new UserDeleteFailed(language, FriendlyMessageCodes.USER_DELETE_FAILED,
                    "User delete failed for user id: " + userId);
        }
        return true;
    }


}
