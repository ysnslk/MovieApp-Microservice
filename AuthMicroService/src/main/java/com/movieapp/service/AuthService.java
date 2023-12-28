package com.movieapp.service;

import com.movieapp.dto.request.CreateAuthRequestDto;
import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.request.UpdateAuthRequestDto;
import com.movieapp.dto.response.AuthResponse;
import com.movieapp.dto.response.InternalApiResponse;
import com.movieapp.dto.response.UserResponse;
import com.movieapp.exception.enums.FriendlyMessageCodes;
import com.movieapp.exception.exceptions.*;
import com.movieapp.manager.IUserManager;
import com.movieapp.mapper.IAuthMapper;
import com.movieapp.repository.IAuthRepository;
import com.movieapp.repository.entity.Auth;
import com.movieapp.repository.enums.ELanguage;
import com.movieapp.repository.enums.EStatus;
import com.movieapp.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, String> {
    private final IAuthRepository authRepository;
    private final IAuthMapper authMapper;
    private final IUserManager userManager;

    public AuthService(MongoRepository<Auth, String> repository, IAuthRepository authRepository, IAuthMapper authMapper, IUserManager userManager) {
        super(repository);
        this.authRepository = authRepository;
        this.authMapper = authMapper;
        this.userManager = userManager;
    }

    public Auth saveAuth(ELanguage language, CreateAuthRequestDto createAuthRequestDto) {
        try {
            Auth auth = authMapper.saveToAuth(createAuthRequestDto);
            CreateUserRequestDto createUserRequestDto = authMapper.convertToCreateUser(createAuthRequestDto);
            String userId = userManager.register(language, createUserRequestDto).getBody();
            auth.setUserId(String.valueOf(userId));
            return save(auth);
        } catch (Exception e) {
            throw new AuthNotCreatedException(language, FriendlyMessageCodes.AUTH_NOT_CREATED_EXCEPTION, "User not created.");
        }
    }

    public Auth updateAuth(ELanguage language, UpdateAuthRequestDto updateAuthRequestDto) {
        try {
            Optional<Auth> auth = findById(updateAuthRequestDto.getAuthId());
            if (auth.isEmpty()) {
                throw new AuthNotFoundException(language, FriendlyMessageCodes.AUTH_NOT_FOUND_EXCEPTION,
                        "User not found for auth id: " + updateAuthRequestDto.getAuthId());
            }
            auth = Optional.ofNullable(authMapper.updateToAuth(updateAuthRequestDto));
            return update(auth.get());
        } catch (Exception e) {
            throw new AuthUpdateFailed(language, FriendlyMessageCodes.AUTH_UPDATE_FAILED,
                    "User update failed for auth id: " + updateAuthRequestDto.getAuthId());
        }
    }

    public List<Auth> findAllAuth(ELanguage language) {
        try {
            return findAll();
        } catch (Exception e) {
            throw new AuthNotFoundException(language, FriendlyMessageCodes.AUTHS_NOT_FOUND_EXCEPTION,
                    "User List not found ");
        }
    }

    public boolean safeDeleteByAuth(ELanguage language, String authId) {
        try {
            Optional<Auth> auth = findById(authId);
            if (auth.isEmpty()) {
                throw new AuthNotFoundException(language, FriendlyMessageCodes.AUTH_NOT_FOUND_EXCEPTION,
                        "User not found for auth id: " + authId);
            }
            if (auth.get().getStatus() == EStatus.DELETED) {
                throw new AuthAlreadyDeletedException(language, FriendlyMessageCodes.AUTH_ALREADY_DELETED,
                        "User already deleted for auth id: " + authId);
            }
            auth.get().setStatus(EStatus.DELETED);
            update(auth.get());
            return true;
        } catch (Exception e) {
            throw new AuthDeleteFailed(language, FriendlyMessageCodes.AUTH_DELETE_FAILED,
                    "User delete failed for auth id: " + authId);
        }
    }
}
