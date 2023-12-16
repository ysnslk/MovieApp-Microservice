package com.movieapp.controller;


import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.request.UpdateUserRequestDto;
import com.movieapp.exception.enums.FriendlyMessageCodes;
import com.movieapp.exception.utils.FriendlyMessageUtils;
import com.movieapp.repository.entity.User;
import com.movieapp.repository.enums.ELanguage;
import com.movieapp.response.FriendlyMessage;
import com.movieapp.response.InternalApiResponse;
import com.movieapp.response.UserResponse;
import com.movieapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.movieapp.constants.ApiUrl.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
@Slf4j
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{language}" + SAVE)
    @Operation(summary = "This endpoint creates user", description = "Create user")
    @CrossOrigin("*")
    public InternalApiResponse<UserResponse> register(@PathVariable("language")ELanguage language,
                                                      @RequestBody CreateUserRequestDto createUserRequestDto){
        User user = userService.saveUser(language, createUserRequestDto);
        UserResponse userResponse = convertUserResponse(user);
        return InternalApiResponse.<UserResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.USER_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(userResponse)
                .build();

    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{language}" + UPDATE +"/{userId}")
    @Operation(summary = "This endpoint updates user", description = "Update user")
    @CrossOrigin("*")
    public InternalApiResponse<UserResponse> userUpdate(@PathVariable("language")ELanguage language,
                                                        @PathVariable("userId") String userId,
                                                        @RequestBody UpdateUserRequestDto updateUserRequestDto){
        log.debug("[{}][updateUser] -> request: {} {}", this.getClass().getSimpleName(), userId, updateUserRequestDto);
        User user = userService.updateUser(language, userId, updateUserRequestDto);
        UserResponse userResponse = convertUserResponse(user);
        log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), userResponse);
        return InternalApiResponse.<UserResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.USER_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(userResponse)
                .build();

    }

    private UserResponse convertUserResponse(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
