package com.movieapp.controller;

import com.movieapp.dto.request.CreateAuthRequestDto;
import com.movieapp.dto.request.UpdateAuthRequestDto;
import com.movieapp.dto.response.AuthResponse;
import com.movieapp.dto.response.FriendlyMessage;
import com.movieapp.dto.response.InternalApiResponse;
import com.movieapp.exception.enums.FriendlyMessageCodes;
import com.movieapp.exception.utils.FriendlyMessageUtils;
import com.movieapp.repository.entity.Auth;
import com.movieapp.repository.enums.ELanguage;
import com.movieapp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.movieapp.constants.ApiUrl.*;
import static com.movieapp.constants.ApiUrl.UPDATE;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
@Slf4j
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{language}" + SAVE)
    @Operation(summary = "This endpoint creates user", description = "Create user")
    @CrossOrigin("*")
    public InternalApiResponse<AuthResponse> register(@PathVariable("language")
                                                      ELanguage language,
                                                      @RequestBody CreateAuthRequestDto createAuthRequestDto) {
        Auth auth = authService.saveAuth(language, createAuthRequestDto);
        AuthResponse authResponse = convertAuthResponse(auth);
        return InternalApiResponse.<AuthResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.USER_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(authResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{language}" + UPDATE)
    @Operation(summary = "This endpoint updates user", description = "Update user")
    @CrossOrigin("*")
    public InternalApiResponse<AuthResponse> userUpdate(@PathVariable("language") ELanguage language,
                                                        @RequestBody UpdateAuthRequestDto updateAuthRequestDto) {

        Auth auth = authService.updateAuth(language, updateAuthRequestDto);
        AuthResponse authResponse = convertAuthResponse(auth);
        return InternalApiResponse.<AuthResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.USER_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(authResponse)
                .build();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{language}" + FIND_ALL)
    @Operation(summary = "This endpoint gets all users", description = "Get all")
    @CrossOrigin("*")
    public InternalApiResponse<List<Auth>> findAll(@PathVariable("language") ELanguage language) {
        List<Auth> auths = authService.findAllAuth(language);
        return InternalApiResponse.<List<Auth>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(auths)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{language}" + DELETE)
    @Operation(summary = "This endpoint deletes user", description = "Delete user")
    @CrossOrigin("*")
    public InternalApiResponse<Boolean> safeDeleteUser(@PathVariable("language") ELanguage language,
                                                       @RequestParam String authId) {

        boolean isDeleted = authService.safeDeleteByAuth(language, authId);
        return InternalApiResponse.<Boolean>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.USER_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(isDeleted)
                .build();

    }

    private AuthResponse convertAuthResponse(Auth auth) {
        return AuthResponse.builder()
                .email(auth.getEmail())
                .password(auth.getPassword())
                .build();
    }
}
