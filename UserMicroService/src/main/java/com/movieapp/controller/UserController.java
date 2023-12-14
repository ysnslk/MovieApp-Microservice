package com.movieapp.controller;


import com.movieapp.dto.request.CreateUserRequestDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.movieapp.constants.ApiUrl.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{language}" + SAVE)
    @Operation(summary = "This endpoint creates user", description = "Create user")
    @CrossOrigin("*")
    public InternalApiResponse<UserResponse> register(@PathVariable("language")ELanguage language,
                                                      @RequestBody CreateUserRequestDto createUserRequestDto){
        User user = userService.saveUser(createUserRequestDto);

        return InternalApiResponse.<UserResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.USER_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .build();

    }
}
