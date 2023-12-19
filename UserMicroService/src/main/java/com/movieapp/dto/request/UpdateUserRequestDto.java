package com.movieapp.dto.request;

import com.movieapp.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequestDto {
    private String userId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
}
