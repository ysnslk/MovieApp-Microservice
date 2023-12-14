package com.movieapp.response;

import com.movieapp.repository.enums.ERole;
import com.movieapp.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
