package com.movieapp.repository.entity;

import com.movieapp.repository.enums.ERole;
import com.movieapp.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth extends BaseEntity {
    @Id
    private String authId;
    private String email;
    private String password;
    private ERole role=ERole.GUEST;
    @Builder.Default
    private EStatus status = EStatus.PASSIVE;

}
