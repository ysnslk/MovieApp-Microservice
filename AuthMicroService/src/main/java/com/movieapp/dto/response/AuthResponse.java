package com.movieapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String name;
    private String surname;
    private String email;
    private String password;
}
