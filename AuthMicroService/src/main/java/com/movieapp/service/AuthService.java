package com.movieapp.service;

import com.movieapp.dto.request.CreateAuthRequestDto;
import com.movieapp.dto.request.UpdateAuthRequestDto;
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

    public AuthService(MongoRepository<Auth, String> repository, IAuthRepository authRepository, IAuthMapper authMapper) {
        super(repository);
        this.authRepository = authRepository;
        this.authMapper = authMapper;
    }

    public Auth saveAuth(ELanguage language, CreateAuthRequestDto createAuthRequestDto) {
        try {
            Auth auth = authMapper.saveToAuth(createAuthRequestDto);
            return save(auth);
        } catch (Exception e) {
            throw new RuntimeException("Kayıt olmadı");
        }
    }

    public Auth updateAuth(ELanguage language, UpdateAuthRequestDto updateAuthRequestDto) {
        try {
            Optional<Auth> auth = findById(updateAuthRequestDto.getAuthId());
            if (auth.isEmpty()) {
                throw new RuntimeException("Kayıt olmadı.");
            }
            auth = Optional.ofNullable(authMapper.updateToAuth(updateAuthRequestDto));
            return update(auth.get());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Auth> findAllAuth(ELanguage language) {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException("Getirilemedi");
        }
    }

    public boolean safeDeleteByAuth(ELanguage language, String authId) {
        try {
            Optional<Auth> auth = findById(authId);
            if (auth.isEmpty()) {
                throw new RuntimeException("Silinemedi");
            }
            if (auth.get().getStatus() == EStatus.DELETED) {
                throw new RuntimeException("Zaten silinmiş");
            }
            auth.get().setStatus(EStatus.DELETED);
            update(auth.get());
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Hata");
        }
    }
}
