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

    public String saveUser(ELanguage language, CreateUserRequestDto createUserRequestDto) {
        try {
            User user = userMapper.saveToUser(createUserRequestDto);
            save(user);
            return user.getUserId();
        } catch (Exception e) {
            throw new UserNotCreatedException(language, FriendlyMessageCodes.USER_NOT_CREATED_EXCEPTION, "User not created.");
        }
    }

    public User updateUser(ELanguage language, UpdateUserRequestDto updateUserRequestDto) {
        try {
            Optional<User> user = findById(updateUserRequestDto.getUserId());
            if (user.isEmpty()) {
                throw new UserNotFoundException(language, FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION,
                        "User not found for user id: " + updateUserRequestDto.getUserId());
            }
            user = Optional.ofNullable(userMapper.updateToUser(updateUserRequestDto));
            return update(user.get());
        } catch (Exception e) {
            throw new UserUpdateFailed(language, FriendlyMessageCodes.USER_UPDATE_FAILED,
                    "User update failed for user id: " + updateUserRequestDto.getUserId());
        }

    }

    public List<User> findAllUser(ELanguage language) {
        try {
            return findAll();
        } catch (Exception e) {
            throw new UserNotFoundException(language, FriendlyMessageCodes.USERS_NOT_FOUND_EXCEPTION,
                    "User List not found ");
        }
    }

    public boolean deleteUser(ELanguage language, String userId) {
        try {
            Optional<User> user = findById(userId);
            if (user.isEmpty()) {
                throw new UserNotFoundException(language, FriendlyMessageCodes.USER_NOT_FOUND_EXCEPTION,
                        "User not found for user id: " + userId);
            }
            if (user.get().getStatus() == EStatus.DELETED) {
                throw new UserAlreadyDeletedException(language, FriendlyMessageCodes.USER_ALREADY_DELETED,
                        "User already deleted for user id: " + userId);
            }
            user.get().setStatus(EStatus.DELETED);
            update(user.get());
            return true;
        } catch (Exception e) {
            throw new UserDeleteFailed(language, FriendlyMessageCodes.USER_DELETE_FAILED,
                    "User delete failed for user id: " + userId);
        }

    }


}
