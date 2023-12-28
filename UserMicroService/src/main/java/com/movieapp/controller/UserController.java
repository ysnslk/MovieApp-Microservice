package com.movieapp.controller;


import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.request.UpdateUserRequestDto;
import com.movieapp.exception.enums.FriendlyMessageCodes;
import com.movieapp.exception.utils.FriendlyMessageUtils;
import com.movieapp.repository.entity.User;
import com.movieapp.repository.enums.ELanguage;
import com.movieapp.dto.response.FriendlyMessage;
import com.movieapp.dto.response.InternalApiResponse;
import com.movieapp.dto.response.UserResponse;
import com.movieapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> register(@PathVariable("language")ELanguage language,
                                   @RequestBody CreateUserRequestDto createUserRequestDto){
        return ResponseEntity.ok(userService.saveUser(language,createUserRequestDto));
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{language}" + UPDATE)
    @Operation(summary = "This endpoint updates user", description = "Update user")
    @CrossOrigin("*")
    public InternalApiResponse<UserResponse> userUpdate(@PathVariable("language")ELanguage language,
                                                        @RequestBody UpdateUserRequestDto updateUserRequestDto){

        User user = userService.updateUser(language, updateUserRequestDto);
        UserResponse userResponse = convertUserResponse(user);
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{language}" + FIND_ALL)
    @Operation(summary = "This endpoint gets all users", description = "Get all")
    @CrossOrigin("*")
    public InternalApiResponse<List<User>> findAll(@PathVariable("language") ELanguage language){
        List<User> users = userService.findAllUser(language);
        return InternalApiResponse.<List<User>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(users)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{language}" + DELETE)
    @Operation(summary = "This endpoint deletes user", description = "Delete user")
    @CrossOrigin("*")
    public InternalApiResponse<Boolean> safeDeleteUser(@PathVariable("language")ELanguage language,
                                                        @RequestParam String  userId){

        boolean isDeleted = userService.deleteUser(language, userId);
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

    private UserResponse convertUserResponse(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
