package com.movieapp.controller;


import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.repository.entity.User;
import com.movieapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.movieapp.constants.ApiUrl.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;


    @PostMapping(SAVE)
    //@PreAuthorize("hasAuthority('ADMIN')")
    @CrossOrigin("*")
    public ResponseEntity<User> register(@RequestBody CreateUserRequestDto dto){
        return ResponseEntity.ok(userService.saveUser(dto));
    }
}
