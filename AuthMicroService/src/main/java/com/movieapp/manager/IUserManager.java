package com.movieapp.manager;

import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.response.InternalApiResponse;
import com.movieapp.dto.response.UserResponse;
import com.movieapp.repository.enums.ELanguage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.movieapp.constants.ApiUrl.*;

@FeignClient(url = "http://localhost:5051/api/v1/user", name = "auth-user")
public interface IUserManager {
    @PostMapping("/{language}" + SAVE)
    ResponseEntity<String> register(@PathVariable("language") ELanguage language,
                            @RequestBody CreateUserRequestDto createUserRequestDto);
}
